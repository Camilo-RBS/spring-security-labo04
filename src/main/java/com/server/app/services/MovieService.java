package com.server.app.services;

import com.server.app.dto.cine.MovieDto;
import com.server.app.entities.Movie;
import com.server.app.exceptions.NotFoundException;
import com.server.app.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<Movie> findAll(int page, int size, String search) {
        return movieRepository.findAll(PageRequest.of(page, size), search == null ? "" : search);
    }

    @Transactional(readOnly = true)
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Película no encontrada: " + id));
    }

    @Transactional
    public Movie create(MovieDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .genre(dto.getGenre())
                .build();
        return movieRepository.save(movie);
    }

    @Transactional
    public Movie update(Long id, MovieDto dto) {
        Movie movie = findById(id);
        if (dto.getTitle() != null) movie.setTitle(dto.getTitle());
        if (dto.getDescription() != null) movie.setDescription(dto.getDescription());
        if (dto.getDuration() != null) movie.setDuration(dto.getDuration());
        if (dto.getGenre() != null) movie.setGenre(dto.getGenre());
        return movieRepository.save(movie);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        movieRepository.deleteById(id);
    }
}
