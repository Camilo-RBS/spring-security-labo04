package com.server.app.services;

import com.server.app.dto.cine.ReservationDto;
import com.server.app.entities.CineFunction;
import com.server.app.entities.Reservation;
import com.server.app.entities.User;
import com.server.app.exceptions.NotFoundException;
import com.server.app.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CineFunctionService cineFunctionService;

    @Transactional
    public Reservation create(ReservationDto dto, User user) {
        CineFunction function = cineFunctionService.findById(dto.getFunctionId());
        BigDecimal totalPrice = function.getPrice().multiply(BigDecimal.valueOf(dto.getSeats()));

        Reservation reservation = Reservation.builder()
                .user(user)
                .function(function)
                .seats(dto.getSeats())
                .totalPrice(totalPrice)
                .build();
        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva no encontrada: " + id));
    }
}
