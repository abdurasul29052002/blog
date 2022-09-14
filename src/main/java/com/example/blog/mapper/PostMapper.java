package com.example.blog.mapper;

import com.example.blog.entity.Post;
import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.PostModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AttachmentMapper.class})
public interface PostMapper extends GenericMapper<Post, PostModel> {
}