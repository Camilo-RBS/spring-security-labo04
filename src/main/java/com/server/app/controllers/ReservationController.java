package com.server.app.controllers;

import com.server.app.dto.cine.ReservationDto;
import com.server.app.entities.Reservation;
import com.server.app.entities.User;
import com.server.app.services.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cine/reservas")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ReservationDto dto) {
        return ResponseEntity.ok(reservationService.create(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }
}
