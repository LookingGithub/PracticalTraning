package com.springboot.hwz.practicaltraning.service.impl;

import com.springboot.hwz.practicaltraning.common.E3Result;
import com.springboot.hwz.practicaltraning.entity.User;
import com.springboot.hwz.practicaltraning.repository.UserRepository;
import com.springboot.hwz.practicaltraning.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl  implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public E3Result userLogin(String username, String password) {
        User user1 = userRepository.findByUsername(username);
        if (user1 == null){
            return E3Result.build(400,"用户名错误");
        }

        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.findByUsernameAndPassword(username, encryptPassword);
        if (user == null){
            return E3Result.build(400,"密码错误");
        }
        return E3Result.ok(user);

    }
}
