package com.example.blog.controller;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.PostModel;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public PostModel addPost(@RequestBody PostModel postModel){
        return postService.addPost(postModel);
    }

    @GetMapping("/{id}")
    public PostModel getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostModel> getUsersAllPosts(){
        return postService.getUsersAllPosts();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePost(@PathVariable Long id){
        return postService.deleteById(id);
    }
}
