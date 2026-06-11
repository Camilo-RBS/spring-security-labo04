package com.server.app.controllers;

import com.server.app.dto.cine.HallDto;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.Hall;
import com.server.app.services.HallService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cine/salas")
@AllArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping
    public ResponseEntity<Pagination<Hall>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Hall> p = hallService.findAll(page, size);
        return ResponseEntity.ok(new Pagination<>(
                p.getContent(),
                new PaginationMeta(p.getNumber(), p.getSize(), p.getTotalPages(), p.getTotalElements())
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hallService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Hall> create(@Valid @RequestBody HallDto dto) {
        return ResponseEntity.ok(hallService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> update(@PathVariable Long id, @Valid @RequestBody HallDto dto) {
        return ResponseEntity.ok(hallService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hallService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
