package entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    // Thêm 2 thuộc tính này để phục vụ chức năng Profile
    private String fullName;
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    // --- BẮT BUỘC PHẢI CÓ GETTER VÀ SETTER ---
    // Bạn có thể tự gõ hoặc chuột phải vào vùng trống -> chọn Generate -> Getter and Setter

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<Post> getPosts() { return posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }
}
