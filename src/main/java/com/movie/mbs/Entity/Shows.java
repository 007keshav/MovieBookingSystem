package com.movie.mbs.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime showTime;

    @Column
    private Double Price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Movie_id", nullable = false)
    private Movie movie;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Theater_id",nullable = false)
    private Theater theater;


    @OneToMany(mappedBy = "show",fetch=FetchType.EAGER)
    private List<Booking> bookings;


    //constructor


    public Shows() {
    }

    public Shows(LocalDateTime showTime, Double price, Movie movie, Theater theater, List<Booking> bookings) {
        this.showTime = showTime;
        Price = price;
        this.movie = movie;
        this.theater = theater;
        this.bookings = bookings;
    }

    public Shows(LocalDateTime showTime, List<Booking> bookings) {
        this.showTime = showTime;
        this.bookings = bookings;
    }

//    public Show(Long id, LocalDateTime showTime, Double price, Movie movie, Theater theater, List<Booking> bookings) {
//        this.id = id;
//        this.showTime = showTime;
//        Price = price;
//        this.movie = movie;
//        this.theater = theater;
//        this.bookings = bookings;
//    }

    //getter and setters

    public  Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
