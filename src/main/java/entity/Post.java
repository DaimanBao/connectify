package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String caption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters...
}