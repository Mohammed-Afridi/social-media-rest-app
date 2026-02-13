package com.projects.socialmedia.social_media;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        User user = userDaoService.findOne(id);

        if(user==null) // when we try to do /users/101 ( User not found )
            throw new UserNotFoundException("id : "+id);
        return user;
    }

    // GET Speicific user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteById(id);
    }

    //POST User
    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@RequestBody User user){
        User savedUser = userDaoService.save(user);

        // Here we are trying to return the location of the created resource to consumer
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
}
