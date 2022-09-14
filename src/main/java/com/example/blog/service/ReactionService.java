package com.example.blog.service;

import com.example.blog.config.SecurityConfiguration;
import com.example.blog.entity.Reaction;
import com.example.blog.entity.User;
import com.example.blog.mapper.ReactionMapper;
import com.example.blog.mapper.UserMapper;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.ReactionModel;
import com.example.blog.repository.ReactionRepository;
import com.example.blog.service.template.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReactionService extends GenericService<Reaction, ReactionModel, ReactionRepository, ReactionMapper> {
    public ReactionService(ReactionMapper mapper, ReactionRepository repository) {
        super(mapper, repository);
    }

    @Autowired
    UserMapper userMapper;

    public ReactionModel addReaction(ReactionModel reactionModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reactionModel.setUser(userMapper.entityToModel(user));
        return save(reactionModel);
    }

    public ApiResponse deleteReactionByPostId(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            repository.deleteByUserIdAndPostId(user.getId(), postId);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
