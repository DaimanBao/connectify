package com.connectify.connectify.repository;

import com.connectify.connectify.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
