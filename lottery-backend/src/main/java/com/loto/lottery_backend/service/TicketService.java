package com.loto.lottery_backend.service;

import com.loto.lottery_backend.entity.Round;
import com.loto.lottery_backend.entity.Ticket;
import com.loto.lottery_backend.repository.RoundRepository;
import com.loto.lottery_backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RoundRepository roundRepository;

    public Ticket payTicket(String userId, String numbers) {
        if (checkUserId(userId) && checkInputNumbers(numbers)){
            Ticket ticket = new Ticket();
            ticket.setUserId(userId);
            List<Integer> numberList = turnNumbersToList(numbers);
            Integer[] userNumbers = numberList.toArray(new Integer[0]);
            ticket.setNumbers(userNumbers);
            Round currRound = roundRepository.findFirstByOrderByStartedAtDesc().orElse(null);
            if (currRound == null){
                return null;
            }
            ticket.setRound(currRound);
            ticketRepository.saveAndFlush(ticket);
            return ticket;
        }
        return null;
    }

    public Ticket getTicket(UUID uuid) {
        return ticketRepository.findById(uuid).orElse(null);
    }

    private boolean checkUserId(String userId){
        if (userId.length() == 20){
            return userId.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+");
        }
        return false;
    }

    private boolean checkInputNumbers(String numbers){
        List<Integer> n = turnNumbersToList(numbers);
        boolean correctCount = (n.size() >= 6 && n.size() <= 10);
        boolean correctRange = n.stream().allMatch(i -> i >= 1 && i <= 45);
        return correctCount && correctRange;
    }

    private List<Integer> turnNumbersToList(String numbers){
        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }
}
