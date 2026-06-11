package com.server.app.controllers;

import com.server.app.dto.cine.CineFunctionDto;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.CineFunction;
import com.server.app.services.CineFunctionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cine/catalogo")
@AllArgsConstructor
public class CineFunctionController {

    private final CineFunctionService cineFunctionService;

    @GetMapping
    public ResponseEntity<Pagination<CineFunction>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String sort) {
        Page<CineFunction> p = cineFunctionService.findAll(page, size, search, sort);
        return ResponseEntity.ok(new Pagination<>(
                p.getContent(),
                new PaginationMeta(p.getNumber(), p.getSize(), p.getTotalPages(), p.getTotalElements())
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CineFunction> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cineFunctionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CineFunction> create(@Valid @RequestBody CineFunctionDto dto) {
        return ResponseEntity.ok(cineFunctionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CineFunction> update(@PathVariable Long id, @Valid @RequestBody CineFunctionDto dto) {
        return ResponseEntity.ok(cineFunctionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cineFunctionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
