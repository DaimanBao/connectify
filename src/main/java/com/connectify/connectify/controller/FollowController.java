package com.connectify.connectify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.connectify.connectify.service.FollowService;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    // ==========================
    // Follow User
    // ==========================
    @GetMapping("/follow/{followerId}/{followingId}")
    public String follow(
            @PathVariable Long followerId,
            @PathVariable Long followingId) {

        followService.follow(
                followerId,
                followingId);

        return "redirect:/followers/1";
    }

    // ==========================
    // Unfollow User
    // ==========================
    @GetMapping("/unfollow/{followerId}/{followingId}")
    public String unfollow(
            @PathVariable Long followerId,
            @PathVariable Long followingId) {

        followService.unfollow(
                followerId,
                followingId);

        return "redirect:/following/1";
    }

    // ==========================
    // Followers Page
    // ==========================
    @GetMapping("/followers/{userId}")
    public String followers(
            @PathVariable Long userId,
            Model model) {

        // Danh sách người đang follow user này
        model.addAttribute(
                "followers",
                followService.getFollowerUsers(userId));

        // Tổng số followers
        model.addAttribute(
                "followersCount",
                followService.countFollowers(userId));

        return "followers";
    }

    // ==========================
    // Following Page
    // ==========================
    @GetMapping("/following/{userId}")
    public String following(
            @PathVariable Long userId,
            Model model) {

        // Danh sách user mà user này đang follow
        model.addAttribute(
                "following",
                followService.getFollowingUsers(userId));

        // Tổng số following
        model.addAttribute(
                "followingCount",
                followService.countFollowing(userId));

        return "following";
    }
}