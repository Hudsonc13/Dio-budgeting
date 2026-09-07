package hsn.budgeting.infra.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final WebSocketAudioHandler audioHandler;

    public WebSocketConfiguration(WebSocketAudioHandler audioHandler) {
        this.audioHandler = audioHandler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(audioHandler, "/ws")
                    .addInterceptors(new HttpSessionHandshakeInterceptor())
                    .setAllowedOrigins("*");
    }





}
