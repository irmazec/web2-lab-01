package com.loto.lottery_backend.entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="rounds")
public class Round {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", nullable = false)
    private UUID id;

    @Column(name="started_at", nullable=false)
    private Timestamp startedAt;

    @Column(name="closed_at", nullable=true)
    private Timestamp closedAt;

    @Column(name = "numbers", nullable=true, columnDefinition = "integer[]")
    private Integer[] numbers;

    @Column(name="active", nullable=false)
    private boolean active;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Round() {
        this.startedAt = Timestamp.from(Instant.now());
        this.active = true;
    }

    public UUID getId() {
        return id;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public Timestamp getClosedAt() {
        return closedAt;
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    public boolean isActive() {
        return active;
    }

    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
