package com.springboot.hwz.practicaltraning.controller;


import com.springboot.hwz.practicaltraning.common.E3Result;
import com.springboot.hwz.practicaltraning.entity.Category;
import com.springboot.hwz.practicaltraning.entity.Movie;
import com.springboot.hwz.practicaltraning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private CategoryService categoryService;


    //主页Home
    @RequestMapping("/")
    public String showHomepage( HttpServletRequest request){

        return "Home";
    }








    //选电影界面
    @RequestMapping("/index")
    public String showIndex( HttpServletRequest request){
        //获取所有分类标签
        E3Result e3ResultAllCategory = categoryService.GetAllCategory();
        List<Category> list1 = (List<Category>)e3ResultAllCategory.getData();
        //获取所有电影数据(缺少筛选，默认一次加载20个)
        Integer Categoryid=0;
        Selectquery query=new Selectquery();
        query.setCategoryid(Categoryid);
        query.setmolimit(0);
        query.setSort("numrating");
        E3Result e3ResultAllMoive = movieService.SortMoiveBycategory(query);
        List<Movie> list2 = (List<Movie>)e3ResultAllMoive.getData();
        //设置SEESION
        request.getSession().setAttribute("category",list1);
        request.getSession().setAttribute("movie",list2);
        return "index";
    }



}
