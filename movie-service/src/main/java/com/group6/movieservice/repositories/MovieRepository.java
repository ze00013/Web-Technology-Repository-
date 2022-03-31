package com.group6.movieservice.repositories;

import com.group6.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findTop10ByOrderByViewsDesc();
    List<Movie> findTop10ByOrderByRatingDesc();
}
