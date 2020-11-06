package com.springboot.hwz.practicaltraning.repository;

import com.springboot.hwz.practicaltraning.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopDefaultMoviesRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT  * FROM topdefaultmovies t,movie m WHERE t.movieid=m.movieid limit 10;",nativeQuery = true)
    List<Movie> findRegDefaultMovie();

}
