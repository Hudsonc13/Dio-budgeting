package hsn.budgeting.infra.websocket;


import hsn.budgeting.application.AI.AiPromptContextUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class WebSocketAudioHandler extends AbstractWebSocketHandler {

    private final AiPromptContextUseCase promptContextUseCase;
    private final Map<String, ByteArrayOutputStream> byteArraySession =
            new ConcurrentHashMap<>();


    private static final Logger logger = LoggerFactory.getLogger(WebSocketAudioHandler.class);

    public WebSocketAudioHandler(AiPromptContextUseCase promptContextUseCase) {
        this.promptContextUseCase = promptContextUseCase;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        session.setBinaryMessageSizeLimit(625000);
        byteArraySession.put(session.getId(), new ByteArrayOutputStream());

        logger.info("Conectado: {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status
    ) {
        logger.info(
                "Conexão fechada. Código: {} Motivo: {}",
                status.getCode(),
                status.getReason()
        );

    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }

    @Override
    public void handleTransportError(
            WebSocketSession session,
            Throwable exception
    ) {
        logger.error("Erro no WebSocket", exception);
    }

    //
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {

        var byteBuffer = message.getPayload();

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);

        var byteArray = byteArraySession.get(session.getId());
        byteArray.write(bytes);


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


        if (message.getPayload().equals("FIM_DO_AUDIO")) {
            var byteArray = byteArraySession.get(session.getId());

            Resource resource = new ByteArrayResource(byteArray.toByteArray()) {
                @Override
                public String getFilename() {
                    return "audio.webm";
                }
            };

            var response = promptContextUseCase.execute(resource);

            session.sendMessage(new TextMessage(response));
        }
    }
}
