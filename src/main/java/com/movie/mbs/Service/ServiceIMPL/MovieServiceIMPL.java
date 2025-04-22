package com.movie.mbs.Service.ServiceIMPL;

import com.movie.mbs.DTO.MovieDTO;
import com.movie.mbs.Entity.Movie;
import com.movie.mbs.Repository.MovieRepository;
import com.movie.mbs.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceIMPL implements MovieService {

    @Autowired
    private MovieRepository movieRepository;




    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        movie.setGenre(movieDTO.getGenre());
        movie.setName(movieDTO.getName());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {

        Optional<List<Movie>> listOfMoviesByGenre =  movieRepository.findByGenre(genre) ;
        if(listOfMoviesByGenre.isPresent()){
            return listOfMoviesByGenre.get();
        }else throw new RuntimeException("No movie found in this genre" + genre);
    }

    @Override
    public Movie getMovieByTitle(String title) {
        Optional<Movie> movieBox = movieRepository.findByName(title);
        if(movieBox.isPresent()){
            return  movieBox.get();
        }else throw new RuntimeException("no movie found with this name "+ movieBox);

    }

    @Override
    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException( "no movie found for the id "+ id));
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        movie.setGenre(movieDTO.getGenre());
        movie.setName(movieDTO.getName());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        return movieRepository.save(movie);
    }

    @Override
    public String deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return "movie with "+id +" has been deleted";
    }

    @Override
    public List<Movie> getMoviesByLanguage(String language) {
        Optional<List<Movie>> listOfMoviesByLanguage =  movieRepository.findByGenre(language) ;
        if(listOfMoviesByLanguage.isPresent()){
            return listOfMoviesByLanguage.get();
        }else throw new RuntimeException("No movie found with this language" + language);
    }

    public List<Movie> searchMoviesByName(String name) {
        return movieRepository.findByNameContainingIgnoreCase(name);
    }

}
