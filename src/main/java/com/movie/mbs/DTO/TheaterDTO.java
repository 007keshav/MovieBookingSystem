package com.movie.mbs.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDTO {

    private String theaterName;

    private String location;

    private Integer theaterCapacity;

    private String theaterScreenType;

}
