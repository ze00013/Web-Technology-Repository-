package com.group6.movieservice.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Director {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;
    private String name;
    @Column(updatable=false)
    @CreationTimestamp
    private Date dateCreated;
    @CreationTimestamp
    private Date lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Director director = (Director) o;
        return id != null && Objects.equals(id, director.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
