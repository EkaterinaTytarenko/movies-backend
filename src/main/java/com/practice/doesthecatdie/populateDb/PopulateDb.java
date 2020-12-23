package com.practice.doesthecatdie.populateDb;

import com.practice.doesthecatdie.servises.CategoryService;
import com.practice.doesthecatdie.servises.MovieService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//populating db with initial data from file + adding a couple of initial categories

@Service
public class PopulateDb {

    @Autowired
    private MovieService moviesService;

    @Autowired
    private CategoryService categoryService;

    public void populate() throws FileNotFoundException {

        InputStream initialFileStream = new FileInputStream(new File("src/main/java/com/practice/doesthecatdie/populateDb/movies.json"));

        JSONArray movies=new JSONArray(new JSONTokener(initialFileStream));


        for (int i=0;i<movies.length();++i) {
            JSONObject movie = movies.getJSONObject(i);
            String title=movie.getString("title");
            String imdb_id=movie.getString("imdb_id");
            int year=movie.getInt("year");
            String picture_url=movie.getString("picture_url");

            String genres=movie.get("genres").toString();
            genres=genres.substring(1,genres.length()-1);
            String[] genresArr=genres.split(",");
            genres="";
            for(int j=0;j<genresArr.length;++j) {
                String genre=genresArr[j];
                genres += genre.substring(1, genre.length() - 1);
                if(j!= genresArr.length-1)
                    genres+=", ";
            }

            moviesService.addNewMovie(title,imdb_id,year,genres,picture_url);
        }

        categoryService.addNewCategory("Is there a dead animal?");
        categoryService.addNewCategory("Is there a child abuse?");
        categoryService.addNewCategory("Is there blood/gore?");
        categoryService.addNewCategory("Does it have a sad ending?");
        categoryService.addNewCategory("Is there an animal abuse?");

    }
}
