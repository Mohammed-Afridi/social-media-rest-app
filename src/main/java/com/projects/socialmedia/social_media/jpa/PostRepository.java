package com.projects.socialmedia.social_media.jpa;

import com.projects.socialmedia.social_media.Post;
import com.projects.socialmedia.social_media.User;
import org.springframework.data.jpa.repository.JpaRepository;

// For users we had userRepository, For posts we need PostRepository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
