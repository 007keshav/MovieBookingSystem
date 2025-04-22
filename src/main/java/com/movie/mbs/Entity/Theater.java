package com.movie.mbs.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Entity
@CrossOrigin
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String theaterName;

    @Column
    private String location;

    @Column
    private Integer theaterCapacity;

    @Column
    private String theaterScreenType;


    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Shows> shows;


    //constructor
    public Theater() {
    }

    public Theater(String theaterName, String location, Integer theaterCapacity, String theaterScreenType) {
        this.theaterName = theaterName;
        this.location = location;
        this.theaterCapacity = theaterCapacity;
        this.theaterScreenType = theaterScreenType;
    }

    //getter and setters


    public Theater(String theaterName, String location, Integer theaterCapacity, String theaterScreenType, List<Shows> shows) {
        this.theaterName = theaterName;
        this.location = location;
        this.theaterCapacity = theaterCapacity;
        this.theaterScreenType = theaterScreenType;
        this.shows = shows;
    }

    public List<Shows> getShows() {
        return shows;
    }

    public void setShows(List<Shows> shows) {
        this.shows = shows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTheaterCapacity() {
        return theaterCapacity;
    }

    public void setTheaterCapacity(Integer theaterCapacity) {
        this.theaterCapacity = theaterCapacity;
    }

    public String getTheaterScreenType() {
        return theaterScreenType;
    }

    public void setTheaterScreenType(String theaterScreenType) {
        this.theaterScreenType = theaterScreenType;
    }
}
