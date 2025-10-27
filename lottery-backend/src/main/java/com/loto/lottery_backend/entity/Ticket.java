package com.loto.lottery_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", nullable = false)
    @JsonBackReference
    private Round round;

    @Column(name="round_number", nullable = true)
    private int round_number;

    @Column(name="user_id", nullable = false, length=20)
    private String userId;

    @Column(name = "numbers", columnDefinition = "integer[]", nullable = false)
    private Integer[] numbers;

    public UUID getId() {
        return id;
    }

    public Round getRound() {
        return round;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}
