package com.springboot.hwz.practicaltraning.repository;

import com.springboot.hwz.practicaltraning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;



public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByUserid(Integer id);


    @Query(value = "UPDATE `user` \n" +
            "SET registertime = ?2, lastlogintime = ?3, `password` = ?4, email = ?5\n" +
            "WHERE userid = ?1;",nativeQuery = true)
    void updateUser(Integer userid, Date registertime, Date lastlogintime , String password, String email);








}
