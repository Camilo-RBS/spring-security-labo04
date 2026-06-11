package com.server.app.dto.cine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MovieDto {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;

    @Min(value = 1, message = "La duración debe ser mayor a 0")
    private Integer duration;

    private String genre;
}
