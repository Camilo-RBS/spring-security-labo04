package com.server.app.dto.cine;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CineFunctionDto {

    @NotNull(message = "El ID de la película es obligatorio")
    private Long movieId;

    @NotNull(message = "El ID de la sala es obligatorio")
    private Long hallId;

    @NotNull(message = "La hora de inicio es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal price;
}
