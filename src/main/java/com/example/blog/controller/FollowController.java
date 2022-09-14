package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.payload.ApiResponse;
import com.example.blog.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ApiResponse follow(@RequestParam Long toUserId){
        return followService.follow(toUserId);
    }

    @PostMapping("/unfollow")
    public ApiResponse unFollow(@RequestParam Long userId){
        return followService.unFollow(userId);
    }

}
