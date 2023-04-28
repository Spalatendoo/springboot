package com.lk.controller;

import com.lk.mapper.UserMapper;
import com.lk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }
    @GetMapping("/queryUserById")
    public User queryUserById(int id){
        User user = userMapper.queryUserById(id);
        return user;
    }
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(9,"yyz","1234"));
        return  "ok";
    }
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(9,"yyx","123444"));
        return "ok";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(9);
        return "ok";
    }

}
