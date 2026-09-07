package hsn.budgeting.infra.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


@Component
public class WebSocketTextHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketTextHandler.class);

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {

        var text = message.getPayload();

        logger.info("Sessao: {}", session.getId());
        logger.info("Dados recebidos: {}", text);

        //Configurar para que ao receber os comandos, a IA crie as transações na tela

        try {
            session.sendMessage(new TextMessage("Mensagem recebida"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
