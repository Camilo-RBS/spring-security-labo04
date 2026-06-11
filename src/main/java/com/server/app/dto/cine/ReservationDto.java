package com.server.app.dto.cine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationDto {

    @NotNull(message = "El ID de la función es obligatorio")
    private Long functionId;

    @NotNull(message = "El número de asientos es obligatorio")
    @Min(value = 1, message = "Debe reservar al menos 1 asiento")
    private Integer seats;
}
