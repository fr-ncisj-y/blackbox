package com.jayplusplus.blackbox.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Constructors, getters, and setters

    // Constructor without generated fields (likeId)
    public Like(UserInfo user, Post post) {
        this.user = user;
        this.post = post;
    }
}
