package com.data.analysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.analysis.entity.User;
import com.data.analysis.service.UserService;
import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> users() {
        return userService.fetchAll();
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/users/update/{id}/{firstname}/{lastname}", method = RequestMethod.PUT)
    @ResponseBody
    public Map update(@PathVariable("id") String id, @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        User user = userService.findUserById(id);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        userService.update(user);
        return ImmutableMap.of("ret", 0, "msg", "ok");
    }
    
    @RequestMapping(value = "/users/create/", method = RequestMethod.POST)
    @ResponseBody
    public Map create(@RequestParam(value = "firstname", required = true) String firstname, @RequestParam(value = "lastname", required = true) String lastname) {
    		log.info("firstname is: " + firstname);
    		log.info("lastname is: " + lastname);
        userService.save(firstname,lastname);
        return ImmutableMap.of("ret", 0, "msg", "ok");
    }
   
}
