package com.loto.lottery_backend.service;

import com.loto.lottery_backend.entity.Round;
import com.loto.lottery_backend.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class LotteryRunningService {
    @Autowired
    private RoundRepository repository;

    public HttpStatus startNewRound() {
        Round newRound = new Round();
        if (isLastRoundClosed()) {
            repository.saveAndFlush(newRound);
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }

    public HttpStatus closeCurrentRound() {
        if(!isLastRoundClosed()){
            repository.findFirstByOrderByStartedAtDesc().ifPresent(
                    lastRow -> {
                        lastRow.setClosedAt(Timestamp.from(Instant.now()));
                        lastRow.setActive(false);
                        repository.save(lastRow);
                    }
            );
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }

    private boolean isLastRoundClosed(){
        if (repository.count() == 0){
            return true;
        }
        Round lastRound = repository.findFirstByOrderByStartedAtDesc().orElse(null);
        return lastRound != null && lastRound.getClosedAt() != null;
    }

    public HttpStatus storeResults(Integer[] chosenNumbers) {

        if (chosenNumbers != null && chosenNumbers.length != 0){
            Round lastRound = repository.findFirstByOrderByStartedAtDesc().orElse(null);
            if (lastRound == null){
                return HttpStatus.BAD_REQUEST;
            }
            if (!lastRound.isActive()){
                lastRound.setNumbers(chosenNumbers);
                repository.save(lastRound);
                return HttpStatus.NO_CONTENT;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }
}
