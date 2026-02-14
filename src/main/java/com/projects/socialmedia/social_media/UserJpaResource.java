package com.projects.socialmedia.social_media;

import com.projects.socialmedia.social_media.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserJpaResource { //Creating this copy from UerResourdce to play with Jpa

    // Because UserDaoService is a component managed by spring autowire it

    private UserDaoService userDaoService;

    private UserRepository repository;

    public UserJpaResource(UserDaoService userDaoService, UserRepository repository) {
        this.userDaoService = userDaoService;
        this.repository = repository;
    }

    // GET Users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    // GET Speicific user
    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id){

        User user = userDaoService.findOne(id);

        if(user==null) // when we try to do /users/101 ( User not found )
            throw new UserNotFoundException("id : "+id);
        return user;
    }

    // Delete Speicific user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteById(id);
    }

    //POST User
    @PostMapping("/jpa/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user){ // @Valid - you can't enter a blank name
        User savedUser = userDaoService.save(user);

        // Here we are trying to return the location of the created resource to consumer
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
}
