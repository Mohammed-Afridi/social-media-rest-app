package com.projects.socialmedia.social_media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)    // When we fetch , we dont want user details to be fetched along with Post
    @JsonIgnore      // We are using Post bean as part of rest api reponses. we dont want User to be part of json reponse for Post bean.
    private User user; // Map the post to a user

    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
