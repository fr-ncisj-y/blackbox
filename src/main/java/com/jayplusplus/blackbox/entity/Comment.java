package com.jayplusplus.blackbox.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    // Constructors, getters, and setters

    // Constructor without generated fields (commentId)
    public Comment(UserInfo user, Post post, String commentText, Date timestamp) {
        this.user = user;
        this.post = post;
        this.commentText = commentText;
        this.timestamp = timestamp;
    }
}

