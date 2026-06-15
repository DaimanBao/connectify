package com.connectify.connectify.repository;

import com.connectify.connectify.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository
        extends JpaRepository<Like, Long> {

    long countByPostId(Long postId);

}
