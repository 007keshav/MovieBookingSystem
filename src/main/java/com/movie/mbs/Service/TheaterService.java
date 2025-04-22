package com.movie.mbs.Service;

import com.movie.mbs.DTO.TheaterDTO;
import com.movie.mbs.Entity.Theater;

import java.util.List;

public interface TheaterService {
    Theater addTheater(TheaterDTO theaterDTO);

    List<Theater> getByLocation(String location);

    Theater updateTheater(Long id , TheaterDTO theaterDTO);

    String deleteTheater(Long id);
}
