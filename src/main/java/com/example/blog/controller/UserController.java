package com.example.blog.controller;

import com.example.blog.entity.Attachment;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.AttachmentModel;
import com.example.blog.payload.PostModel;
import com.example.blog.payload.UserModel;
import com.example.blog.service.AttachmentService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserModel> getAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return userService.findAll(page,size);
    }

    @GetMapping
    public UserModel getUserById(){
        return userService.getUserById();
    }

    @PutMapping
    public UserModel updateUser(@RequestBody UserModel userModel){
        return userService.update(userModel);
    }

    @DeleteMapping
    public ApiResponse deleteUser(){
        return userService.deleteUser();
    }

    @PostMapping("/upload")
    public AttachmentModel uploadProfilePhoto(MultipartRequest multipartRequest){
        return userService.uploadProfilePhoto(multipartRequest);
    }

    @GetMapping("/download")
    public void downloadProfilePhoto(HttpServletResponse response, @RequestParam Long userId){
        userService.downloadProfilePhoto(response, userId);
    }
}
