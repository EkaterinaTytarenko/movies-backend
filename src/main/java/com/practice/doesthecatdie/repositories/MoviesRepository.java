package com.practice.doesthecatdie.repositories;

import com.practice.doesthecatdie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

    public boolean existsByImdbid(String imdb_id);

}
