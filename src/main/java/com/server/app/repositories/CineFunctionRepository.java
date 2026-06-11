package com.server.app.repositories;

import com.server.app.entities.CineFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CineFunctionRepository extends JpaRepository<CineFunction, Long> {

    @Query("SELECT f FROM CineFunction f WHERE " +
            "LOWER(f.movie.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.hall.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<CineFunction> findAll(Pageable pageable, @Param("query") String query);
}
