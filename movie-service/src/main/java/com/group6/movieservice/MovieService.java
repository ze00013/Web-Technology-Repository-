package com.group6.movieservice;

import com.group6.movieservice.serializers.ResponseMessageDTO;
import com.group6.movieservice.serializers.MovieRequestDTO;
import com.group6.movieservice.serializers.MovieResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MovieService {
    ResponseMessageDTO uploadPoster(UUID movieId, MultipartFile file);
    MovieResponseDTO createMovie(MovieRequestDTO request);
    MovieResponseDTO updateMovie(UUID movieId, MovieRequestDTO request);
    MovieResponseDTO getSingleMovie(UUID movieId);
    ResponseMessageDTO deleteMovie(UUID movieId);
    Page<MovieResponseDTO> getAllMovies(int page, int size);
    List<MovieResponseDTO> getPopularMovies();
    List<MovieResponseDTO> topRatedMovies();
}
