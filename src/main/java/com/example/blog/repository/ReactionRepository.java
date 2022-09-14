package com.example.blog.repository;

import com.example.blog.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    void deleteByUserIdAndPostId(Long user_id, Long post_id);
}
