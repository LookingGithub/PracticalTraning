package com.springboot.hwz.practicaltraning.service;

import com.springboot.hwz.practicaltraning.entity.Movie;

import java.util.List;

public interface TopDefaultMoviesService {
    //选择默认的电影10个在用户注册时用
    public List<Movie> findRegDefaultMovie();
}
