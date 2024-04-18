package com.jayplusplus.blackbox.service.impl;

import com.jayplusplus.blackbox.entity.Post;
import com.jayplusplus.blackbox.model.GenericResponse;
import com.jayplusplus.blackbox.model.PostResponse;
import com.jayplusplus.blackbox.model.PostResponseModel;
import com.jayplusplus.blackbox.repository.PostRepository;
import com.jayplusplus.blackbox.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public GenericResponse savePost(Post post) {
        GenericResponse response = new GenericResponse();
        Post resp = postRepository.save(post);
        if(resp.getPostId() != null){
            response.setMessage("OK");
            response.setCode(00);
        }else{
            response.setMessage("Failed to saved post");
            response.setCode(100);
        }

        return response;
    }

    @Override
    public GenericResponse editPost(Post post) {
        return null;
    }

    @Override
    public GenericResponse deletePost(Integer id) {
        return null;
    }

    @Override
    public PostResponse getAllPost(String username) {
        PostResponse responses = new PostResponse();
        List<PostResponseModel> post = postRepository.findPostsByUserId(username);

        responses.setUsername(username);
        responses.setPosts(post);
        if (post.isEmpty()){
            responses.setCode(10);
            responses.setMessage("No post available");
        }else{
            responses.setCode(00);
            responses.setMessage("Successful");
        }


        return responses;
    }
}
