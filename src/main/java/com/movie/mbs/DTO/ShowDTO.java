package com.movie.mbs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO {
    private LocalDateTime showTime;
    private Double price;
    private Long movieId;
    private Long theaterId;


}
