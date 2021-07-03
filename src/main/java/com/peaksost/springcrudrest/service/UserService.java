package com.peaksost.springcrudrest.service;


import com.peaksost.springcrudrest.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User  getById(Long id);

    User addUser(User user);

    User updateUser(User user);

    void deleteById(Long id);


    User findByUsername(String s);

}
