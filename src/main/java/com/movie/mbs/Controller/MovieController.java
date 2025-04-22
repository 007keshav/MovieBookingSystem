package com.movie.mbs.Controller;

import com.movie.mbs.DTO.MovieDTO;
import com.movie.mbs.Entity.Movie;
import com.movie.mbs.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {


    @Autowired
    private MovieService movieService;


    @PostMapping("/addMovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }


    @GetMapping("getAllMovies")
    public ResponseEntity<List<Movie> >getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }


   @GetMapping("/getMoviesByGenera")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }


    @GetMapping("/getMoviesByLanguage")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language){
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }

    @GetMapping("/getMovieByTitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }


    @PutMapping("/updateMovie/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.updateMovie(id,movieDTO));
    }

    @DeleteMapping("/deleteMovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String name) {
        List<Movie> result = movieService.searchMoviesByName(name);
        return ResponseEntity.ok(result);
    }

}
