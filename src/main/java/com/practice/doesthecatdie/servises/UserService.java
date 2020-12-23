package com.practice.doesthecatdie.servises;

import com.practice.doesthecatdie.models.*;
import com.practice.doesthecatdie.repositories.CategoriesRepository;
import com.practice.doesthecatdie.repositories.MoviesRepository;
import com.practice.doesthecatdie.repositories.UserRatingRepository;
import com.practice.doesthecatdie.repositories.UserRepository;
import com.practice.doesthecatdie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRatingRepository userRatingRepository;


    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(s);

        if(user.isEmpty())
            return null;

        return new MyUserDetails(user.get());

    }

    public MyUserDetails loadUserByEmail(String s) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(s);

        if(user.isEmpty())
            return null;

        return new MyUserDetails(user.get());
    }

    public User loadUserById(Long id) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findById(id);

        if(user.isEmpty())
            return null;

        return user.get();
    }

    /*when adding a new movie we add rating 0 for all categories and all movies*/

    public User addNewUser(String userName, String email, String password, String role){

        User user=new User(userName,email,password,role);
        user=userRepository.save(user);

        List<Category> allCategories= (List<Category>) categoriesRepository.findAll();
        List<Movie> allMovies= (List<Movie>) moviesRepository.findAll();

        for(Category category:allCategories){
            for(Movie movie:allMovies){
                UserRating newUserRating=new UserRating();
                newUserRating.setCategory(category);
                newUserRating.setMovie(movie);
                newUserRating.setUser(user);

                userRatingRepository.saveAndFlush(newUserRating);
                moviesRepository.saveAndFlush(movie);
                categoriesRepository.saveAndFlush(category);
            }

        }

        userRepository.saveAndFlush(user);
        return user;
    }

    public void activateUser(User user){
        user.setValid(true);
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> findAllAdmins(){
        return userRepository.findByRole("ADMIN");
    }

}
