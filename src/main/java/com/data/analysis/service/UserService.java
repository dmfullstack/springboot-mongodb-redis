package com.data.analysis.service;

import java.util.List;

import com.data.analysis.entity.User;

public interface UserService {

    List<User> fetchAll();
    
    void save(String firstname, String lastname);

    User findUserById(String id);

    void update(User user);

    void remove(String id);
    
    User findByFirstName(String firstName);
    
    User findByLastName(String lastName);

}
