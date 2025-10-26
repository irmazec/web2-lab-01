package com.loto.lottery_backend.dto;

public record TicketDTO (
        String userId,
        Integer[] numbers
) {
}
