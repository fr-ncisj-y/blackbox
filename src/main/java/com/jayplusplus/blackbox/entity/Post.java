package com.jayplusplus.blackbox.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserInfo user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 1500)
    private String content;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    // Constructors, getters, and setters

    // Constructor without generated fields (postId)
    public Post(UserInfo user, String title, String content, Date timestamp) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }
    public Post() {
        // Default constructor needed by JPA
    }
}

