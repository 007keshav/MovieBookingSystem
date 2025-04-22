package com.movie.mbs.Controller;

import com.movie.mbs.DTO.BookingDTO;
import com.movie.mbs.Entity.Booking;
import com.movie.mbs.Entity.BookingStatus;
import com.movie.mbs.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getuserbooking/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getUserBooking(id));
    }


    @GetMapping("/getshowbooking/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getShowBooking(id));
    }


    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking (@PathVariable long id){
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking (@PathVariable long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/getbookingsbystatus/{bookingstatus}")
    public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable BookingStatus bookingStatus) {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(bookingStatus));
    }

}
