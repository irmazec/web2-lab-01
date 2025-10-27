package com.loto.lottery_backend.controller;

import com.loto.lottery_backend.entity.Round;
import com.loto.lottery_backend.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://web2-lab-01.onrender.com")
public class RoundController {
    @Autowired
    private RoundRepository repository;

    @GetMapping("/app/last-round")
    public ResponseEntity<Round>getLastRound(){
        return ResponseEntity.of(repository.findFirstByOrderByStartedAtDesc());
    }
}
