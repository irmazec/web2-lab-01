package com.loto.lottery_backend.controller;

import com.loto.lottery_backend.dto.ChosenNumbersDTO;
import com.loto.lottery_backend.service.LotteryRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://web2-lab-01-frontend.onrender.com/")
public class AdminController {
    @Autowired
    private LotteryRunningService service;

    @PostMapping("/app/new-round")
    public ResponseEntity<HttpStatus> startNewRound(){
        return ResponseEntity.status(service.startNewRound()).build();
    }

    @PostMapping("/app/close")
    public ResponseEntity<HttpStatus> closeNewRound(){
        return ResponseEntity.status(service.closeCurrentRound()).build();
    }

    @PostMapping("/app/store-results")
    public ResponseEntity<HttpStatus> storeResults(@RequestBody ChosenNumbersDTO chosenNumbersDTO){
        return ResponseEntity.status(service.storeResults(chosenNumbersDTO.numbers())).build();
    }

}
