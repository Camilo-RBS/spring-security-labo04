package com.server.app.repositories;

import com.server.app.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.genre) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Movie> findAll(Pageable pageable, @Param("query") String query);
}
