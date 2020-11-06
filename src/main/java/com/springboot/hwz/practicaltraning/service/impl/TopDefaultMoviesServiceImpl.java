package com.springboot.hwz.practicaltraning.service.impl;

import com.springboot.hwz.practicaltraning.entity.Movie;
import com.springboot.hwz.practicaltraning.repository.TopDefaultMoviesRepository;
import com.springboot.hwz.practicaltraning.service.TopDefaultMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopDefaultMoviesServiceImpl implements TopDefaultMoviesService {

    @Autowired
    private TopDefaultMoviesRepository topDefaultMoviesRepository;

    @Override
    public List<Movie> findRegDefaultMovie() {

        return topDefaultMoviesRepository.findRegDefaultMovie();
    }
}
