package com.projects.socialmedia.social_media;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    // Because UserDaoService is a component managed by spring autowire it

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // GET Users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    // GET Speicific user
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return  userDaoService.findOne(id);
    }

    //POST User
    @PostMapping("/users")
    public void CreateUser(@RequestBody User user){
        userDaoService.save(user);
    }
}
