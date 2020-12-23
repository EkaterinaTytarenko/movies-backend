package com.practice.doesthecatdie.repositories;

import com.practice.doesthecatdie.models.UserRating;
import com.practice.doesthecatdie.models.UserRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRatingRepository extends JpaRepository<UserRating, UserRatingKey> {

    @Query(value="select * from user_rating  " +
            "where category_id = :category_id and movie_id = :movie_id " +
            "and user_id=:user_id",nativeQuery = true)
    public Optional<UserRating> findUserRating(@Param("category_id") Long category_id, @Param("movie_id") Long movie_id,
                                               @Param("user_id") Long user_id);

    @Query(value="select * from user_rating where movie_id = :movie_id",nativeQuery = true)
    public List<UserRating> findByMovieId(@Param("movie_id") Long movie_id);
}
