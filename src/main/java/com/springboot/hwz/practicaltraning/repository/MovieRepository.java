package com.springboot.hwz.practicaltraning.repository;

import com.springboot.hwz.practicaltraning.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository  extends JpaRepository<Movie, Integer> {


    @Query(value = "SELECT *\n" +
            "FROM movie\n" +
            "WHERE movieid = ?1;",nativeQuery = true)
    Movie selectById(Integer id);

}
