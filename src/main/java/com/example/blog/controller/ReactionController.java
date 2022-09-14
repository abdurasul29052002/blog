package com.example.blog.controller;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.ReactionModel;
import com.example.blog.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping
    public ReactionModel addReaction(@RequestBody ReactionModel reactionModel){
        return reactionService.addReaction(reactionModel);
    }

    @DeleteMapping
    public ApiResponse deleteReactionByPostId(@RequestParam Long postId){
        return reactionService.deleteReactionByPostId(postId);
    }
}
