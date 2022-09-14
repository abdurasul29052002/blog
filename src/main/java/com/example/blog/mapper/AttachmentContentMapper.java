package com.example.blog.mapper;

import com.example.blog.entity.AttachmentContent;
import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.AttachmentContentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentContentMapper extends GenericMapper<AttachmentContent, AttachmentContentModel> {
}
