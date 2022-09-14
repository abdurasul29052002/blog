package com.example.blog.service;

import com.example.blog.entity.Attachment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.mapper.PostMapper;
import com.example.blog.mapper.UserMapper;
import com.example.blog.payload.AttachmentModel;
import com.example.blog.payload.PostModel;
import com.example.blog.repository.AttachmentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.template.GenericService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService extends GenericService<Post, PostModel, PostRepository, PostMapper> {

    public PostService(PostMapper mapper, PostRepository repository) {
        super(mapper, repository);
    }

    @Autowired
    UserMapper userMapper;
    @Autowired
    AttachmentService attachmentService;

    public PostModel addPost(PostModel postModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postModel.setUser(userMapper.entityToModel(user));
        return save(postModel);
    }

    public PostModel getPostById(Long id) {
        return findById(id);
    }

    public List<PostModel> getUsersAllPosts() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapper.entitiesToModels(repository.findAllByUserId(user.getId()));
    }
}
