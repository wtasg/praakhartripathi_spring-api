package com.websocket_broadcast_server.controller;

import com.websocket_broadcast_server.service.BroadcastService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BroadcastController {
    private final BroadcastService service;

    public BroadcastController(BroadcastService service) {
        this.service = service;
    }

    @RequestMapping("/broadcast")
    public void broadcast(@RequestBody String message) throws IOException {
        service.broadcast(message);
    }
}
