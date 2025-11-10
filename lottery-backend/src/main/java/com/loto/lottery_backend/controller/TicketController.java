package com.loto.lottery_backend.controller;

import com.google.zxing.WriterException;
import com.loto.lottery_backend.dto.TicketDTO;
import com.loto.lottery_backend.entity.Ticket;
import com.loto.lottery_backend.service.QRGenerator;
import com.loto.lottery_backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("/app/ticket")
@CrossOrigin(origins = "https://web2-lab-01-frontend.onrender.com")
@RestController
public class TicketController {
    @Autowired
    private TicketService service;
    @Value("${app.url}")
    private String hostBase;

    @PostMapping("/pay")
    public ResponseEntity<byte[]> payTicket(@RequestParam String userId, @RequestParam String numbers){

        Ticket ticket = service.payTicket(userId, numbers);
        if (ticket != null) {
            String path = hostBase + "/ticket/" + ticket.getId();
            try {
                byte[] qr = QRGenerator.generateQrCode(path);
                return ResponseEntity.ok()
                        .header("Content-Type", "image/png")
                        .body(qr);
            } catch (IOException | WriterException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable UUID uuid){
        Ticket ticket = service.getTicket(uuid);
        if (ticket != null){
            TicketDTO ticketDTO = new TicketDTO(ticket.getUserId(), ticket.getNumbers());
            return ResponseEntity.ok(ticketDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
