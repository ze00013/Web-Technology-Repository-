package com.group6.movieservice.repositories;

import com.group6.movieservice.models.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CastRepository extends JpaRepository<Cast, UUID> {
}
