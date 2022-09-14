package com.example.blog.payload;

import com.example.blog.entity.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionModel {
    private Long id;

    private ReactionType reactionType;

    private PostModel post;

    private UserModel user;
}
