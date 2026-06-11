package com.server.app.controllers;

import com.server.app.dto.cine.MovieDto;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.Movie;
import com.server.app.services.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cine/peliculas")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Pagination<Movie>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String search) {
        Page<Movie> p = movieService.findAll(page, size, search);
        return ResponseEntity.ok(new Pagination<>(
                p.getContent(),
                new PaginationMeta(p.getNumber(), p.getSize(), p.getTotalPages(), p.getTotalElements())
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> create(@Valid @RequestBody MovieDto dto) {
        return ResponseEntity.ok(movieService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @Valid @RequestBody MovieDto dto) {
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
