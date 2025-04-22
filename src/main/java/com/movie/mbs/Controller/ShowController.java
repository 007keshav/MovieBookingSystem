package com.movie.mbs.Controller;

import com.movie.mbs.DTO.ShowDTO;
import com.movie.mbs.Entity.Shows;
import com.movie.mbs.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Show")
public class ShowController {

    @Autowired
    private ShowService showService;


    @PostMapping("/createshow")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shows> createShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.createShow(showDTO));
    }

    @GetMapping("/getallshow")
    public ResponseEntity<List<Shows>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/getshowsbymovie/{Id}")
    public ResponseEntity<List<Shows>> getShowsByMovies(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByMovies(id));
    }


    @GetMapping("/getshowsbytheater/{id}")
    public ResponseEntity<List<Shows>> getShowsByTheater(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByTheater(id));
    }


    @PutMapping("/updateshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shows> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.updateShow(id,showDTO));
    }


    @DeleteMapping("/deleteshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteShowById(@PathVariable Long id){
        showService.deleteShowById(id);
        return ResponseEntity.ok().build();
    }


}
