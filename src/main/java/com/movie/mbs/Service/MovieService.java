package com.movie.mbs.Service;

import com.movie.mbs.DTO.MovieDTO;
import com.movie.mbs.Entity.Movie;

import java.util.List;

public interface MovieService {
    Movie addMovie(MovieDTO movieDTO);

    List<Movie> getAllMovies();

    List<Movie> getMoviesByGenre(String genre);

    Movie getMovieByTitle(String title);

    Movie updateMovie(Long id, MovieDTO movieDTO);

    String deleteMovie(Long id);

    List<Movie> getMoviesByLanguage(String language);

    List<Movie> searchMoviesByName(String name);
}
