package com.group6.movieservice.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue
    private UUID id;
    private String posterUrl;
    private String title;
    private String summary;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String trailerUrl;
    private Date releaseDate;
    private int views = 0;
    private double rating = 0.0;
    @OneToMany(mappedBy="movie")
    @ToString.Exclude
    private Set<Director> directors;
    @OneToMany(mappedBy="movie")
    @ToString.Exclude
    private Set<Cast> casts;
    @Column(updatable=false)
    @CreationTimestamp
    private Date dateCreated;
    @CreationTimestamp
    private Date lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
