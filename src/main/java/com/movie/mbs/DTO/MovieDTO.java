package com.movie.mbs.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String name;

    private String description;

    private String genre;

    private String language;

    private Integer duration;

    private LocalDate releaseDate;

}
