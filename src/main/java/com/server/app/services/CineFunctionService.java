package com.server.app.services;

import com.server.app.dto.cine.CineFunctionDto;
import com.server.app.entities.CineFunction;
import com.server.app.entities.Hall;
import com.server.app.entities.Movie;
import com.server.app.exceptions.NotFoundException;
import com.server.app.repositories.CineFunctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CineFunctionService {

    private final CineFunctionRepository cineFunctionRepository;
    private final MovieService movieService;
    private final HallService hallService;

    @Transactional(readOnly = true)
    public Page<CineFunction> findAll(int page, int size, String search, String sort) {
        Sort sorting = (sort != null && !sort.isBlank())
                ? Sort.by(Sort.Direction.ASC, sort)
                : Sort.by(Sort.Direction.ASC, "startTime");
        return cineFunctionRepository.findAll(
                PageRequest.of(page, size, sorting),
                search == null ? "" : search
        );
    }

    @Transactional(readOnly = true)
    public CineFunction findById(Long id) {
        return cineFunctionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Función no encontrada: " + id));
    }

    @Transactional
    public CineFunction create(CineFunctionDto dto) {
        Movie movie = movieService.findById(dto.getMovieId());
        Hall hall = hallService.findById(dto.getHallId());

        CineFunction function = CineFunction.builder()
                .movie(movie)
                .hall(hall)
                .startTime(dto.getStartTime())
                .price(dto.getPrice())
                .build();
        return cineFunctionRepository.save(function);
    }

    @Transactional
    public CineFunction update(Long id, CineFunctionDto dto) {
        CineFunction function = findById(id);

        if (dto.getMovieId() != null) {
            function.setMovie(movieService.findById(dto.getMovieId()));
        }
        if (dto.getHallId() != null) {
            function.setHall(hallService.findById(dto.getHallId()));
        }
        if (dto.getStartTime() != null) {
            function.setStartTime(dto.getStartTime());
        }
        if (dto.getPrice() != null) {
            function.setPrice(dto.getPrice());
        }
        return cineFunctionRepository.save(function);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        cineFunctionRepository.deleteById(id);
    }
}
