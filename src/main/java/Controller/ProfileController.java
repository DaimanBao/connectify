package Controller;

import Repositories.PostRepository;
import Repositories.UserRepository;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Xem hồ sơ cá nhân và danh sách bài viết
    @GetMapping
    public String viewProfile(Principal principal, Model model) {
        // Giả sử lấy user đang đăng nhập (Authentication Module phụ trách phần này)
        String username = principal != null ? principal.getName() : "testUser";
        User user = userRepository.findByUsername(username);

        List<Post> userPosts = postRepository.findByUserOrderByCreatedAtDesc(user);
        long postCount = postRepository.countByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("posts", userPosts);
        model.addAttribute("postCount", postCount); // Thống kê số bài viết

        return "profile";
    }

    // Giao diện chỉnh sửa thông tin cá nhân
    @GetMapping("/edit")
    public String editProfileForm(Principal principal, Model model) {
        String username = principal != null ? principal.getName() : "testUser";
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "edit-profile";
    }

    // Xử lý lưu thông tin chỉnh sửa
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute User updatedUser, Principal principal) {
        String username = principal != null ? principal.getName() : "testUser";
        User currentUser = userRepository.findByUsername(username);

        currentUser.setFullName(updatedUser.getFullName());
        currentUser.setBio(updatedUser.getBio());
        // Cập nhật các trường khác như avatar...

        userRepository.save(currentUser);
        return "redirect:/profile";
    }
}