package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
                System.out.println("===webSocketSession==="+webSocketSession);

            }

            @Override
            public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
                System.out.println("===webSocketSession==="+webSocketSession);

            }

            @Override
            public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
                System.out.println("===webSocketSession==="+webSocketSession);

            }

            @Override
            public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
                System.out.println("===webSocketSession==="+webSocketSession);

            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }
        }, "/ws")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
