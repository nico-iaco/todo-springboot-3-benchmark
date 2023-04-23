package it.iacovelli.todospringboot3benchmark.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TodoDto(UUID id, String message, LocalDateTime publishedDate) {
}
