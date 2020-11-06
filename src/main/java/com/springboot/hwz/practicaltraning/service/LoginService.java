package com.springboot.hwz.practicaltraning.service;

import com.springboot.hwz.practicaltraning.common.E3Result;

public interface LoginService {
    E3Result userLogin(String username,String  password);

}
