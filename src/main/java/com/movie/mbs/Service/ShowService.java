package com.movie.mbs.Service;

import com.movie.mbs.DTO.ShowDTO;
import com.movie.mbs.Entity.Shows;

import java.util.List;

public interface ShowService {
    Shows createShow(ShowDTO showDTO);

    List<Shows> getAllShows();

    List<Shows> getShowsByMovies(Long id);


    List<Shows> getShowsByTheater(Long id);

    Shows updateShow(Long id, ShowDTO showDTO);

    String deleteShowById(Long id);
}
