package com.projects.socialmedia.social_media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User { // If we use User then error because user is table , change the name


    protected User() {
    }

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, message="Names should have more than two characters")
    private String name;
    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;

    //In the user entity , I want add list of posts by the user
    @OneToMany(mappedBy = "user") // A single user can have many posts, hence onetomany. mappedBy = "user", this is the user field from Post.java. owner of the realtionship is user
    @JsonIgnore   // We are using User bean as part of rest api reponses. we dont want post to be part of json reponse for User bean.
    private List<Post> posts;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
