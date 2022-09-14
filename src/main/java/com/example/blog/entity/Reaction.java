package com.example.blog.entity;

import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;

    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
}
