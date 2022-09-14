package com.example.blog.mapper;

import com.example.blog.entity.Reaction;
import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.ReactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReactionMapper extends GenericMapper<Reaction, ReactionModel> {
}
