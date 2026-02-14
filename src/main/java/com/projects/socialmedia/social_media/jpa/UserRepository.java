package com.projects.socialmedia.social_media.jpa;

import com.projects.socialmedia.social_media.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
