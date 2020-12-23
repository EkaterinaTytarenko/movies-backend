package com.practice.doesthecatdie.controllers;

import com.practice.doesthecatdie.dtos.MovieDetailsDTO;
import com.practice.doesthecatdie.dtos.MoviesSearchDTO;
import com.practice.doesthecatdie.servises.CategoryService;
import com.practice.doesthecatdie.servises.MovieService;
import com.practice.doesthecatdie.servises.RatingsService;
import com.practice.doesthecatdie.servises.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private MovieService moviesService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RatingsService ratingsService;

    @GetMapping("/index")
    public List<MoviesSearchDTO> loadMovies() throws FileNotFoundException {
        return moviesService.findAllMovies();
    }

    @PostMapping("/movieDetails")
    public MovieDetailsDTO loadMovieDetails(@RequestBody Map<String,Long> request) {
        return moviesService.getMovieDetails( request.get("movie_id"));
    }

    @PostMapping("/userRequest")
    public String userRequest(@RequestBody Map<String,String> request){
        try {
            requestService.makeUserRequest(request.get("userId"), request.get("request"));
            return "Your request was sent! We`ll try to process it as soon as possible";
        }
        catch (Exception e){
            return "Internal server error! Please, try to send a request later";
        }
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Map<String,String> request){

        String name=request.get("category_name");
        if(categoryService.existsCategory(name))
            return "Such category already exists!";

        categoryService.addNewCategory(name);
        return "Successfully added new category!";
    }

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Map<String,String> request){
        String imdb_id=request.get("imdb_ID");
        if(moviesService.existByImdb_id(imdb_id))
            return "Such movie`s already added.";
        try {
            moviesService.addNewMovieByImdb_id(imdb_id);
            return "The chosen movie was successfully added!";
        }
        catch (Exception e){
            return "Internal error!Please, try to add this movie later";
        }

    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(@RequestBody Map<String,String> request){
        moviesService.deleteMovie(Long.parseLong(request.get("movie_id")));
        return "Movie was successfully deleted!";
    }

    @PostMapping("/changeRating")
    public void changeRating(@RequestBody Map<String,String> request) {

        ratingsService.changeTotalRating(Long.parseLong(request.get("movie_id")),
                Long.parseLong(request.get("category_id")), Integer.parseInt(request.get("total_rating")));

        ratingsService.changeUserRating(Long.parseLong(request.get("movie_id")),
                Long.parseLong(request.get("category_id")),Long.parseLong(request.get("user_id")), Integer.parseInt(request.get("user_rating")));
    }

}
