package com.jayplusplus.blackbox.controller;

import com.jayplusplus.blackbox.entity.Post;
import com.jayplusplus.blackbox.model.GenericResponse;
import com.jayplusplus.blackbox.model.PostResponse;
import com.jayplusplus.blackbox.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    @Autowired
    PostService service;
    @PostMapping("/save")
    public GenericResponse addUser(@RequestBody Post post){
        return service.savePost(post);
    }

    @GetMapping("/getPost/all/{username}")
    public PostResponse getAllPost(@PathVariable String username){
        return service.getAllPost(username);
    }
}
