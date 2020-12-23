package com.practice.doesthecatdie.servises;

import com.practice.doesthecatdie.models.*;
import com.practice.doesthecatdie.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private TotalRatingRepository totalRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRatingRepository userRatingRepository;

    /*when adding a new category we add rating 0 for all movies and all users*/

    public void addNewCategory(String name){

        Category category=new Category(name);
         category=categoriesRepository.save(category);

        List<Movie> allMovies= (List<Movie>) moviesRepository.findAll();
        List<User> allUsers= (List<User>) userRepository.findAll();

        for(Movie movie:allMovies){
            TotalRating newTotalRating =new TotalRating();
            newTotalRating.setCategory(category);
            newTotalRating.setMovie(movie);

            totalRatingRepository.saveAndFlush(newTotalRating);

            for(User user:allUsers){
                UserRating newUserRating=new UserRating();
                newUserRating.setCategory(category);
                newUserRating.setMovie(movie);
                newUserRating.setUser(user);

                userRatingRepository.saveAndFlush(newUserRating);
                userRepository.saveAndFlush(user);
            }

            moviesRepository.saveAndFlush(movie);
        }
        categoriesRepository.saveAndFlush(category);
    }

    public boolean existsCategory(String name){
        return categoriesRepository.existsByName(name);
    }
}
