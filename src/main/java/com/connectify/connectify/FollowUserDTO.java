package com.connectify.connectify;

public class FollowUserDTO {

    private Long id;
    private String username;
    private String email;

    public FollowUserDTO(
            Long id,
            String username,
            String email) {

        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}