package com.movie.mbs.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfSeats;

    private Double price;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="booking_seat_number")
    private List<String> seatNumbers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="show_id", nullable = false)
    private Shows show;


    public Booking() {
    }

    public Booking(Long id, int numberOfSeats, Double price, LocalDateTime bookingTime, BookingStatus bookingStatus, List<String> seatNumbers, User user, Shows show) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
        this.seatNumbers = seatNumbers;
        this.user = user;
        this.show = show;
    }

    public Booking(int numberOfSeats, Double price, LocalDateTime bookingTime, BookingStatus bookingStatus, List<String> seatNumbers, User user, Shows show) {
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
        this.seatNumbers = seatNumbers;
        this.user = user;
        this.show = show;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shows getShow() {
        return show;
    }

    public void setShow(Shows show) {
        this.show = show;
    }
}
