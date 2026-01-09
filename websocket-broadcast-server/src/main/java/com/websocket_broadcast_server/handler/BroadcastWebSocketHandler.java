package com.websocket_broadcast_server.handler;

import com.websocket_broadcast_server.service.BroadcastService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class BroadcastWebSocketHandler extends TextWebSocketHandler {
    private final BroadcastService broadcastService;

    public BroadcastWebSocketHandler(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        broadcastService.addSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        broadcastService.broadcast(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        broadcastService.removeSession(session);
    }
}
