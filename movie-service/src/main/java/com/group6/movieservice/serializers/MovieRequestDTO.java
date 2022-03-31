package com.group6.movieservice.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRequestDTO {
    private String title;
    private String summary;
    private String description;
    private String trailerUrl;
    private Date releaseDate;
    private Set<DirectorDTO> directors;
    private Set<CastDTO> casts;
}
