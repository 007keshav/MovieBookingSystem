package com.movie.mbs.Repository;

import com.movie.mbs.Entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Shows, Long> {
    Optional<List<Shows>> findByMovieId(Long movieid);
    Optional<List<Shows>> findByTheaterId(Long theaterid);
}
