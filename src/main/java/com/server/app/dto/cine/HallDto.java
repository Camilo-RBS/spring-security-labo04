package com.server.app.dto.cine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HallDto {

    @NotBlank(message = "El nombre de la sala es obligatorio")
    private String name;

    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Integer capacity;
}
