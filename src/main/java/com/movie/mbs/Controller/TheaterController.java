package com.movie.mbs.Controller;

import com.movie.mbs.DTO.TheaterDTO;
import com.movie.mbs.Entity.Theater;
import com.movie.mbs.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/theater")
public class TheaterController {


    @Autowired
    private TheaterService theaterService;


    @PostMapping("/addTheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
    }


    @GetMapping("/getTheaterByLocation")
    public ResponseEntity<List<Theater>> getByLocation(@RequestParam String location){
        return ResponseEntity.ok(theaterService.getByLocation(location));
    }

    @PutMapping("/updateTheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.ok(theaterService.updateTheater(id, theaterDTO));
    }

    @DeleteMapping("/deleteTheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTheater(@PathVariable Long id){
        return ResponseEntity.ok(theaterService.deleteTheater(id));
    }



}
