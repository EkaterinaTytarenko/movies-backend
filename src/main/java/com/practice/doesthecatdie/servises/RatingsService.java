package com.practice.doesthecatdie.servises;

import com.practice.doesthecatdie.models.TotalRating;
import com.practice.doesthecatdie.models.UserRating;
import com.practice.doesthecatdie.repositories.TotalRatingRepository;
import com.practice.doesthecatdie.repositories.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingsService {

    @Autowired
    TotalRatingRepository totalRatingRepository;

    @Autowired
    UserRatingRepository userRatingRepository;

    public void changeUserRating(Long movie_id,Long category_id,Long user_id,int new_rating){

        UserRating rating=userRatingRepository.findUserRating(category_id,movie_id,user_id).get();
        rating.setRating(new_rating);
        userRatingRepository.saveAndFlush(rating);
    }

    public void changeTotalRating(Long movie_id,Long category_id,int new_rating){

        TotalRating rating=totalRatingRepository.findTotalRating(category_id,movie_id).get();
        rating.setRating(new_rating);
       totalRatingRepository.saveAndFlush(rating);

    }

}
