package com.springboot.hwz.practicaltraning.service.impl;

import com.springboot.hwz.practicaltraning.entity.User;
import com.springboot.hwz.practicaltraning.repository.UserRepository;
import com.springboot.hwz.practicaltraning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class UserSeriviceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findByUserid(id);
    }

    @Override
    public void updateUser(Integer userid, String password, String email) {

        Date registertime = new Date();
        Date lastLogintime = new Date();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        userRepository.updateUser(userid,registertime,lastLogintime,md5Password,email);

    }
}
