package com.movie.mbs.Service.ServiceIMPL;

import com.movie.mbs.DTO.TheaterDTO;
import com.movie.mbs.Entity.Theater;
import com.movie.mbs.Repository.TheaterRepository;
import com.movie.mbs.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterServiceIMPL implements TheaterService {


    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();

        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setLocation(theaterDTO.getLocation());
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    @Override
    public List<Theater> getByLocation(String location) {
        Optional<List<Theater>> listOfTheaterBox  = theaterRepository.findByLocation(location);
        if(listOfTheaterBox.isPresent()){
            return listOfTheaterBox.get();
        }else throw new RuntimeException("No Theater found at this location "+location);
    }

    @Override
    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("No theater found with this id no updating "+id)
                );

        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setLocation(theaterDTO.getLocation());
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    @Override
    public String deleteTheater(Long id) {
        theaterRepository.deleteById(id);
        return "Theater with " +id+" has been  deleted" ;
    }
}
