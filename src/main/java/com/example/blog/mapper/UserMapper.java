package com.example.blog.mapper;

import com.example.blog.entity.User;
import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserModel> {
}
