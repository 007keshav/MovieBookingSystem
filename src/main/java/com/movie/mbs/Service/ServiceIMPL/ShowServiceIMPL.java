package com.movie.mbs.Service.ServiceIMPL;

import com.movie.mbs.DTO.ShowDTO;
import com.movie.mbs.Entity.Booking;
import com.movie.mbs.Entity.Movie;
import com.movie.mbs.Entity.Shows;
import com.movie.mbs.Entity.Theater;
import com.movie.mbs.Repository.MovieRepository;
import com.movie.mbs.Repository.ShowRepository;
import com.movie.mbs.Repository.TheaterRepository;
import com.movie.mbs.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceIMPL implements ShowService {


    @Autowired
    private ShowRepository showRepository;


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;




    @Override
    public Shows createShow(ShowDTO showDTO) {

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()-> new RuntimeException("No Movie Found"));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No theater found for id "+showDTO.getTheaterId()));

        Shows show = new Shows();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepository.save(show);

    }

    @Override
    public List<Shows> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public List<Shows> getShowsByMovies(Long movieid) {
        Optional<List<Shows>> movieBox =  showRepository.findByMovieId(movieid);

        if(movieBox.isPresent()){
            return movieBox.get();
        }else throw new RuntimeException("No shows available for this movie ");

    }

    @Override
    public List<Shows> getShowsByTheater(Long theaterid) {
        Optional<List<Shows>> theaterBox =  showRepository.findByTheaterId(theaterid);

        if(theaterBox.isPresent()){
            return theaterBox.get();
        }else throw new RuntimeException("No theater found with this name ");

    }

    @Override
    public Shows updateShow(Long id, ShowDTO showDTO) {
         Shows show = showRepository.findById(id).orElseThrow(()-> new RuntimeException("now show found with this id " + id));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()-> new RuntimeException("No Movie Found"));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No theater found for id "+showDTO.getTheaterId()));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepository.save(show);

    }

    @Override
    public String deleteShowById(Long id) {
        if(!showRepository.existsById(id)){
            throw new RuntimeException("No show available with this id");
        }
        List<Booking> bookings = showRepository.findById(id).get().getBookings();
        if(!bookings.isEmpty()){
            throw new RuntimeException("no show available with this id");
        }
        showRepository.deleteById(id);
        return " show has been deleted successfully with id " + " id";
    }
}
