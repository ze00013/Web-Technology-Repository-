package com.group6.movieservice;

import com.group6.movieservice.serializers.ResponseMessageDTO;
import com.group6.movieservice.serializers.MovieRequestDTO;
import com.group6.movieservice.serializers.MovieResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping(value="{movieId}/upload-poster", consumes = "multipart/form-data")
    // @ApiOperation(value="Create poster")
    public ResponseEntity<ResponseMessageDTO> uploadPoster(@PathVariable(value="movieId") UUID movieId, @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.uploadPoster(movieId, file));
    }

    @PostMapping("")
    // @ApiOperation(value="Create a movie")
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @PutMapping("{movieId}")
//    @ApiOperation(value="Update a movie")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable(value="movieId") UUID movieId, @RequestBody MovieRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.updateMovie(movieId, request));
    }

    @GetMapping("{movieId}")
//    @ApiOperation(value="Get single movie")
    public ResponseEntity<MovieResponseDTO> getSingleMovie(@PathVariable(value="movieId") UUID movieId) {
        return ResponseEntity.ok(movieService.getSingleMovie(movieId));
    }

    @DeleteMapping("{movieId}")
//    @ApiOperation(value="Delete movie")
    public ResponseEntity<ResponseMessageDTO> deleteMovie(@PathVariable(value="movieId") UUID movieId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movieService.deleteMovie(movieId));
    }

    @GetMapping("")
//    @ApiOperation(value="Get all movies")
    public ResponseEntity<Page<MovieResponseDTO>> getSingleMovie(@RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @GetMapping("popular")
//    @ApiOperation(value="Get popular movies")
    public ResponseEntity<List<MovieResponseDTO>> getPopularMovies() {
        return ResponseEntity.ok(movieService.getPopularMovies());
    }

    @GetMapping("top-rated")
//    @ApiOperation(value="Get top rated movies")
    public ResponseEntity<List<MovieResponseDTO>> topRatedMovies() {
        return ResponseEntity.ok(movieService.topRatedMovies());
    }
}
