package com.practice.doesthecatdie.repositories;

import com.practice.doesthecatdie.models.TotalRating;
import com.practice.doesthecatdie.models.TotalRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TotalRatingRepository extends JpaRepository<TotalRating, TotalRatingKey> {
    @Query(value="select * from total_rating  " +
            "where category_id = :category_id and movie_id = :movie_id",nativeQuery = true)
    public Optional<TotalRating> findTotalRating(@Param("category_id") Long category_id, @Param("movie_id") Long movie_id);

    @Query(value="select * from total_rating where movie_id = :movie_id",nativeQuery = true)
    public List<TotalRating> findByMovieId(@Param("movie_id") Long movie_id);
}
