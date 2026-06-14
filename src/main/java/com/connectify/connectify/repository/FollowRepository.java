package com.connectify.connectify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.connectify.connectify.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowerId(Long followerId);

    List<Follow> findByFollowingId(Long followingId);

    @Modifying
    @Transactional
    void deleteByFollowerIdAndFollowingId(
            Long followerId,
            Long followingId);

    boolean existsByFollowerIdAndFollowingId(
            Long followerId,
            Long followingId);
    
    long countByFollowingId(Long followingId);

    long countByFollowerId(Long followerId);
}