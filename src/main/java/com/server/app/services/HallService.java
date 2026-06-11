package com.server.app.services;

import com.server.app.dto.cine.HallDto;
import com.server.app.entities.Hall;
import com.server.app.exceptions.NotFoundException;
import com.server.app.repositories.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    @Transactional(readOnly = true)
    public Page<Hall> findAll(int page, int size) {
        return hallRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Hall findById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sala no encontrada: " + id));
    }

    @Transactional
    public Hall create(HallDto dto) {
        Hall hall = Hall.builder()
                .name(dto.getName())
                .capacity(dto.getCapacity())
                .build();
        return hallRepository.save(hall);
    }

    @Transactional
    public Hall update(Long id, HallDto dto) {
        Hall hall = findById(id);
        if (dto.getName() != null) hall.setName(dto.getName());
        if (dto.getCapacity() != null) hall.setCapacity(dto.getCapacity());
        return hallRepository.save(hall);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        hallRepository.deleteById(id);
    }
}
