package com.connectify.connectify.controller;

import com.connectify.connectify.entity.Post;
import com.connectify.connectify.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.connectify.connectify.entity.User;
import com.connectify.connectify.repository.UserRepository;
import com.connectify.connectify.repository.LikeRepository;
import com.connectify.connectify.entity.Like;
import com.connectify.connectify.entity.Comment;
import com.connectify.connectify.repository.CommentRepository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public HomeController(
            PostRepository postRepository,
            UserRepository userRepository,
            LikeRepository likeRepository,
            CommentRepository commentRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {

        List<Post> posts = postRepository.findAll();

        for(Post post : posts){

            long totalLikes =
                    likeRepository.countByPostId(post.getId());

            post.setLikeCount(totalLikes);

            List<Comment> comments =
                    commentRepository.findByPostId(post.getId());

            post.setComments(comments);
        }

        model.addAttribute("posts", posts);

        return "home";
    }
    @PostMapping("/create-post")
    public String createPost(
            @RequestParam String caption) {

        User user = userRepository
                .findById(1L)
                .orElse(null);

        Post post = new Post();

        post.setCaption(caption);

        post.setUser(user);

        postRepository.save(post);

        return "redirect:/home";
    }
    @PostMapping("/like")
    public String likePost(
            @RequestParam Long postId) {

        User user = userRepository
                .findById(1L)
                .orElse(null);

        Post post = postRepository
                .findById(postId)
                .orElse(null);

        if(user != null && post != null){

            Like like = new Like();

            like.setUser(user);

            like.setPost(post);

            likeRepository.save(like);
        }

        return "redirect:/home";
    }
    @PostMapping("/comment")
    public String createComment(
            @RequestParam Long postId,
            @RequestParam String content) {

        User user = userRepository
                .findById(1L)
                .orElse(null);

        Post post = postRepository
                .findById(postId)
                .orElse(null);

        if(user != null && post != null){

            Comment comment = new Comment();

            comment.setContent(content);

            comment.setUser(user);

            comment.setPost(post);

            commentRepository.save(comment);
        }

        return "redirect:/home";
    }
    @PostMapping("/delete-post")
    public String deletePost(
            @RequestParam Long postId) {

        postRepository.deleteById(postId);

        return "redirect:/home";
    }
}