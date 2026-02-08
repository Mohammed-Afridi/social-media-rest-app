package com.projects.socialmedia.social_media;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userscount = 0;

    static{
        users.add(new User(++userscount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userscount,"Goli", LocalDate.now().minusYears(23)));
        users.add(new User(++userscount,"Baje", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        // here first thought which comes is how to do , Normal programmer will use
        // for loop on user and get the right user
        Predicate<? super User> predicate = user -> user.getId().equals(id);
         return users.stream().filter(predicate).findFirst().get();
    }

    public User save(User user){
        user.setId(++userscount);
        users.add(user);
        return user;
    }
}
