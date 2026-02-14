package com.projects.socialmedia.social_media;

import com.projects.socialmedia.social_media.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource { //Creating this copy from UerResourdce to play with Jpa

    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    // GET Users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return  repository.findAll();
    }

    // GET Speicific user
    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) // when we try to do /users/101 ( User not found )
            throw new UserNotFoundException("id : "+id);
        return user.get();
    }

    // Delete Speicific user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
    }

    //POST User
    @PostMapping("/jpa/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user){ // @Valid - you can't enter a blank name
        User savedUser = repository.save(user);

        // Here we are trying to return the location of the created resource to consumer
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
}
