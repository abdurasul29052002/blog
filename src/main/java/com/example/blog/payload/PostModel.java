package com.example.blog.payload;

import com.example.blog.entity.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
    private Long id;

    private String title;

    private String text;

    private List<AttachmentModel> attachments;

    private UserModel user;

    private Map<ReactionType, Integer> reactionCount = new HashMap<>();
}
