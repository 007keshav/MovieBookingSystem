package com.movie.mbs.Service;

import com.movie.mbs.DTO.BookingDTO;
import com.movie.mbs.Entity.Booking;
import com.movie.mbs.Entity.BookingStatus;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingDTO bookingDTO);

    List<Booking> getUserBooking(Long id);

    List<Booking> getShowBooking(Long id);

    Booking confirmBooking(long id);

    Booking cancelBooking(long id);
    List<Booking> getBookingsByStatus(BookingStatus status);

}
