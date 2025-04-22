package com.movie.mbs.Entity;

import java.util.List;
import java.util.stream.Collectors;

public enum BookingStatus {
    CONFIRMED,
    CANCELLED,
    PENDING;

//    public List<Booking> getBookingsByStatus(BookingStatus bookingStatus) {
//        return booking.stream()
//                .filter(booking -> booking.getBookingStatus() == bookingStatus)
//                .collect(Collectors.toList());
//    }

    public static List<Booking> getBookingsByStatus(List<Booking> bookings, BookingStatus status) {
        return bookings.stream()
                .filter(booking -> booking.getBookingStatus() == status)
                .collect(Collectors.toList());
    }
}
