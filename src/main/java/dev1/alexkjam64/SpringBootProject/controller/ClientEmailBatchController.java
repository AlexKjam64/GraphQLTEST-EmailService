package dev1.alexkjam64.SpringBootProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev1.alexkjam64.SpringBootProject.service.ClientEmailService;

@RestController
@RequestMapping("/batch")
public class ClientEmailBatchController {
    private final ClientEmailService clientEmailService;

    public ClientEmailBatchController(ClientEmailService clientEmailService){
        this.clientEmailService = clientEmailService;
    }

    @PostMapping
    public ResponseEntity<?> batchEmail(@RequestBody List<Integer> ids){
        return ResponseEntity.ok(clientEmailService.retrieveAllEmails(ids));
    }
}
