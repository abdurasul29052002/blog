package com.example.blog.entity;

import com.example.blog.entity.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;
    @Transient
    private Map<ReactionType, Integer> reactionCount = new HashMap<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Attachment> attachments;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reaction> reactions = new ArrayList<>();

    public Map<ReactionType, Integer> getReactionCount() {
        ReactionType[] values = ReactionType.values();
        for (ReactionType value : values) {
            reactionCount.put(value,0);
        }

        for (Reaction reaction : reactions) {
            reactionCount.put(reaction.getReactionType(),reactionCount.get(reaction.getReactionType())+1);
        }
        return reactionCount;
    }
}
