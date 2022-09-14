package com.example.blog.mapper;

import com.example.blog.entity.Attachment;
import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.AttachmentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AttachmentContentMapper.class})
public interface AttachmentMapper extends GenericMapper<Attachment, AttachmentModel> {
}
