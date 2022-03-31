package com.group6.movieservice;

import com.group6.movieservice.exceptions.MovieNotFoundException;
import com.group6.movieservice.models.Director;
import com.group6.movieservice.models.Movie;
import com.group6.movieservice.repositories.MovieRepository;
import com.group6.movieservice.serializers.ResponseMessageDTO;
import com.group6.movieservice.serializers.MovieRequestDTO;
import com.group6.movieservice.serializers.MovieResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;


    @Override
    public ResponseMessageDTO uploadPoster(UUID movieId, MultipartFile file) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty())
            throw new MovieNotFoundException("Movie not found");
        return new ResponseMessageDTO("Image uploaded successfully");
    }

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO request) {
        Movie movie = new ModelMapper().map(request, Movie.class);
        movie = movieRepository.save(movie);
        return new ModelMapper().map(movie, MovieResponseDTO.class);
    }

    @Override
    public MovieResponseDTO updateMovie(UUID movieId, MovieRequestDTO request) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty())
            throw new MovieNotFoundException("Movie not found");
        new ModelMapper().map(request, movie.get());
        movieRepository.save(movie.get());
        return new ModelMapper().map(movie.get(), MovieResponseDTO.class);
    }

    @Override
    public MovieResponseDTO getSingleMovie(UUID movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty())
            throw new MovieNotFoundException("Movie not found");
        return new ModelMapper().map(movie.get(), MovieResponseDTO.class);
    }

    @Override
    public ResponseMessageDTO deleteMovie(UUID movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty())
            throw new MovieNotFoundException("Movie not found");
        movieRepository.delete(movie.get());
        return new ResponseMessageDTO("Movie deleted successfully");
    }

    @Override
    public Page<MovieResponseDTO> getAllMovies(int page, int size) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("dateCreated").descending());
        return movieRepository.findAll(pageable).map(entity -> new ModelMapper().map(entity, MovieResponseDTO.class));
    }

    @Override
    public List<MovieResponseDTO> getPopularMovies() {
        return Arrays.asList(new ModelMapper().map(movieRepository.findTop10ByOrderByViewsDesc(), MovieResponseDTO[].class));
    }

    @Override
    public List<MovieResponseDTO> topRatedMovies() {
        return Arrays.asList(new ModelMapper().map(movieRepository.findTop10ByOrderByRatingDesc(), MovieResponseDTO[].class));
    }
}
