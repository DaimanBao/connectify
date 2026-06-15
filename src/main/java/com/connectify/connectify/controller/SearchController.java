package com.connectify.connectify.controller;

import com.connectify.connectify.entity.User;
import com.connectify.connectify.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final UserRepository userRepository;


    public SearchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false)
            String keyword,

            Model model) {

        List<User> users;

        if(keyword == null || keyword.isEmpty()) {

            users = userRepository.findAll();

        }
        else {

            users = userRepository.findByUsernameContaining(keyword);

        }

        model.addAttribute("users", users);

        return "search";
    }

}
