package com.springboot.hwz.practicaltraning.controller;

import com.springboot.hwz.practicaltraning.common.E3Result;
import com.springboot.hwz.practicaltraning.entity.Browse;
import com.springboot.hwz.practicaltraning.entity.Movie;
import com.springboot.hwz.practicaltraning.entity.User;
import com.springboot.hwz.practicaltraning.service.LoginService;
import com.springboot.hwz.practicaltraning.service.RegisterService;
import com.springboot.hwz.practicaltraning.service.TopDefaultMoviesService;
import com.springboot.hwz.practicaltraning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private TopDefaultMoviesService topDefaultMoviesService;

    @Autowired
    private UserService userService;


    @RequestMapping("/page/login")
    public String login(){
        return "login";
    }


    //判断登录账号是否存在
    @ResponseBody
    @PostMapping(value = "/customer/login")
    public E3Result login(String username, String password, Model model,HttpServletRequest request){
        E3Result e3Result = loginService.userLogin(username, password);
        User user = null;
        //判断是否登录成功
        if (e3Result.getStatus() == 200){
            user = (User)e3Result.getData();
        }
        request.getSession().setAttribute("user", user);
        //返回结果
        return e3Result;
    }


    //进入注册页面
    @RequestMapping("/page/register")
    public String reg(HttpServletRequest request) {
        //选择topmovies给用户选择她喜欢的电影
        List<Movie> list = topDefaultMoviesService.findRegDefaultMovie();
        request.getSession().setAttribute("TopRegDefaultMovie",list);
        return "register";
    }


    //检查用户名/邮箱是否符合规范(在没有点击注册按钮前检查)
    @RequestMapping("/customer/check/{param}/{type}")
    @ResponseBody
    public E3Result checkData(@PathVariable String param, @PathVariable Integer type)   {
        //后端decode解码(如果前端输入的是中文)
        try {
            String str = URLDecoder.decode(param, "UTF-8");
            E3Result e3Result = registerService.checkData(str, type);
            return e3Result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  null;
        }
    }

    //点击注册按钮后先对用户名和邮箱进行检查
    @RequestMapping("/customer/checkboth/{paramName}/{paramEmail}/{type}")
    @ResponseBody
    public E3Result checkDataBoth(@PathVariable String paramName,@PathVariable String paramEmail, @PathVariable Integer type) {
        //后端进行decode如果前端传中文值
        try {
            String str = URLDecoder.decode(paramName, "UTF-8");
            E3Result e3Result = registerService.checkDataBoth(str,paramEmail,type);
            return e3Result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  null;
        }
    }

    //对用户进行注册(在全部检查完成后)
    @RequestMapping(value = "/customer/register", method = RequestMethod.POST)
    @ResponseBody
    public E3Result register(User user,HttpServletRequest request) {
        //修改3.18 返回用户id,用于用户选择喜欢的电影后把相应信息存broswer表
        Integer userId = 0;
        E3Result e3Result = registerService.register(user);
        if (e3Result.getStatus() == 200) {
            userId = (Integer) e3Result.getData();
        }
        request.getSession().setAttribute("userId", userId);
        return e3Result;
    }



    //用户退出
    @RequestMapping("/page/logout")
    public String pagelogout( HttpServletRequest request){
        //注销seesion
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userstar");
        request.getSession().removeAttribute("reviews");
        request.getSession().removeAttribute("booluserunlikedmovie");
        request.getSession().removeAttribute("TopRegDefaultMovie");
        return "Home";

    }


    // 更新用户密码
    @RequestMapping("/user/update")
    @ResponseBody
    public String updateUser(HttpServletRequest request) {
        String useridstr = request.getParameter("userid");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Integer userid = Integer.parseInt(useridstr);
        // 修改密码
        userService.updateUser(userid, password, email);
        return "OK";
    }

    @RequestMapping("/user/edit")
    @ResponseBody
    public User getUserById(Integer id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping(value = "/customer/register/movieSubmit")
    @ResponseBody
    public String selectedMoive(String ids, HttpServletRequest request){

        if(ids == "" || ids == null){
            System.out.println("用户没有选择电影");
            return "fail";
        }else {
            //获取用户的id
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            Browse browse = new Browse();
            browse.setBrowseid(userId);
            browse.setmovieids(ids);
            registerService.selectFavorite(browse);
            return "ok";
        }

    }




}
