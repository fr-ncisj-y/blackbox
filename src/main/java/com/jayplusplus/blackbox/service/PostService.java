package com.jayplusplus.blackbox.service;

import com.jayplusplus.blackbox.entity.Post;
import com.jayplusplus.blackbox.model.GenericResponse;
import com.jayplusplus.blackbox.model.PostResponse;

import java.util.List;

public interface PostService {
    GenericResponse savePost(Post post);
    GenericResponse editPost(Post post);
    GenericResponse deletePost(Integer id);
    PostResponse getAllPost (String username);
}
