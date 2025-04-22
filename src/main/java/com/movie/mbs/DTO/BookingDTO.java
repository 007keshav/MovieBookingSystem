package com.movie.mbs.DTO;

import com.movie.mbs.Entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private Integer numberOfSeats;
    private Double price;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;
    private Long userId;
    private Long showId;
    private List<String> seatNumbers;

}
