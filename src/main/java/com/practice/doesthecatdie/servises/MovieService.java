package com.practice.doesthecatdie.servises;

import com.practice.doesthecatdie.dtos.MovieDetailsDTO;
import com.practice.doesthecatdie.dtos.MoviesSearchDTO;
import com.practice.doesthecatdie.models.*;
import com.practice.doesthecatdie.repositories.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

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

    /*when adding a new movie we add rating 0 for all categories and all users*/

    public void addNewMovie(String title,String imdb_id, int year, String genres, String picture_url){

        Movie movie=new Movie(title,imdb_id,year,genres,picture_url);
        movie=moviesRepository.save(movie);

        List<Category> allCategories= (List<Category>) categoriesRepository.findAll();
        List<User> allUsers= (List<User>) userRepository.findAll();

        for(Category category:allCategories){
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

            categoriesRepository.saveAndFlush(category);
        }

        moviesRepository.saveAndFlush(movie);

    }

    public List<MoviesSearchDTO> findAllMovies(){

        List<MoviesSearchDTO> movies = moviesRepository
                .findAll()
                .stream()
                .map(MoviesSearchDTO::new)
                .collect(Collectors.toList());

        return movies;
    }

    public MovieDetailsDTO getMovieDetails(Long movie_id){

        MovieDetailsDTO movie = new MovieDetailsDTO
                (moviesRepository.findById(movie_id).get());

        return movie;
    }

    public boolean existByImdb_id(String imdb_id){
        return moviesRepository.existsByImdbid(imdb_id);
    }

    public void addNewMovieByImdb_id(String imdb_id) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://movie-database-imdb-alternative.p.rapidapi.com/?i="+imdb_id+"&r=json")
                .get()
                .addHeader("x-rapidapi-key", "")
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject responseJSON=new JSONObject(response.body().string());

        addNewMovie(responseJSON.getString("Title"),imdb_id,responseJSON.getInt("Year"),
                responseJSON.getString("Genre"),responseJSON.getString("Poster"));
    }

    public void deleteMovie(Long movie_id){

        Movie movie=moviesRepository.findById(movie_id).get();
        List<UserRating> userRatings=userRatingRepository.findByMovieId(movie_id);
        List<TotalRating> totalRatings=totalRatingRepository.findByMovieId(movie_id);

        totalRatingRepository.deleteAll(totalRatings);
        userRatingRepository.deleteAll(userRatings);
        moviesRepository.delete(movie);

    }
}
