package com.springboot.hwz.practicaltraning.service;

import com.springboot.hwz.practicaltraning.common.E3Result;
import com.springboot.hwz.practicaltraning.entity.Browse;
import com.springboot.hwz.practicaltraning.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

public interface RegisterService {

    //检查输入是否合法实时检测
    public E3Result checkData(String param, int type);
    //注册用户
    public E3Result register(User user);
    public void selectFavorite(Browse browse);//注册时候写入用户喜欢的电影
    //点击注册按钮时候检查合法性
    E3Result checkDataBoth(@PathVariable String paramName, @PathVariable String paramEmail, @PathVariable Integer type);
}
