package com.connectify.connectify.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectify.connectify.model.Follow;
import com.connectify.connectify.repository.FollowRepository;

import com.connectify.connectify.model.User;
import com.connectify.connectify.FollowUserDTO;
import com.connectify.connectify.repository.UserRepository;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    // Follow
    public void follow(Long followerId, Long followingId) {

        if (!followRepository.existsByFollowerIdAndFollowingId(
                followerId,
                followingId)) {

            Follow follow = new Follow();

            follow.setFollowerId(followerId);
            follow.setFollowingId(followingId);
            follow.setCreatedAt(LocalDateTime.now());

            followRepository.save(follow);
        }
    }

    // Unfollow
    public void unfollow(Long followerId, Long followingId) {

        followRepository.deleteByFollowerIdAndFollowingId(
                followerId,
                followingId);
    }

    // Followers
    public List<Follow> getFollowers(Long userId) {

        return followRepository.findByFollowingId(userId);
    }

    // Following
    public List<Follow> getFollowing(Long userId) {

        return followRepository.findByFollowerId(userId);
    }
    
    //Thống kê
    public long countFollowers(Long userId) {

        return followRepository.countByFollowingId(userId);
    }

    public long countFollowing(Long userId) {

        return followRepository.countByFollowerId(userId);
    }
    public List<FollowUserDTO> getFollowingUsers(Long userId) {

        List<Follow> follows =
                followRepository.findByFollowerId(userId);

        List<FollowUserDTO> result =
                new ArrayList<>();

        for (Follow follow : follows) {

            User user =
                    userRepository.findById(
                            follow.getFollowingId())
                            .orElse(null);

            if (user != null) {

                result.add(
                        new FollowUserDTO(
                                user.getId(),
                                user.getUsername(),
                                user.getEmail()));
            }
        }

        return result;
    }
    public List<FollowUserDTO> getFollowerUsers(Long userId) {

        List<Follow> follows =
                followRepository.findByFollowingId(userId);

        List<FollowUserDTO> result =
                new ArrayList<>();

        for (Follow follow : follows) {

            User user =
                    userRepository.findById(
                            follow.getFollowerId())
                            .orElse(null);

            if (user != null) {

                result.add(
                        new FollowUserDTO(
                                user.getId(),
                                user.getUsername(),
                                user.getEmail()));
            }
        }

        return result;
    }
}