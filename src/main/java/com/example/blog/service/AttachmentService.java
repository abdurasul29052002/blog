package com.example.blog.service;

import com.example.blog.entity.*;
import com.example.blog.mapper.AttachmentMapper;
import com.example.blog.payload.AttachmentContentModel;
import com.example.blog.payload.AttachmentModel;
import com.example.blog.repository.AttachmentContentRepository;
import com.example.blog.repository.AttachmentRepository;
import com.example.blog.service.template.GenericService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class AttachmentService extends GenericService<Attachment, AttachmentModel, AttachmentRepository, AttachmentMapper> {

    public AttachmentService(AttachmentMapper mapper, AttachmentRepository repository) {
        super(mapper, repository);
    }

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public List<AttachmentModel> addAttachment(MultipartRequest multipartRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        List<AttachmentModel> attachmentModels = new ArrayList<>();
        while (fileNames.hasNext()) {
            String next = fileNames.next();
            MultipartFile file = multipartRequest.getFile(next);
            if (repository.existsByUserId(user.getId())){
                Attachment attachment = repository.findByUserId(user.getId()).orElseThrow();
                AttachmentContentModel attachmentContentModel = new AttachmentContentModel(null, Objects.requireNonNull(file).getBytes());
                AttachmentModel attachmentModel = new AttachmentModel(null, Objects.requireNonNull(file).getOriginalFilename(), file.getContentType(), file.getSize(), attachmentContentModel);
                update(attachmentModel, attachment.getId());
                Attachment savedAttachment = repository.findByUserId(user.getId()).orElseThrow();
                attachmentModels.add(mapper.entityToModel(savedAttachment));
            }else {
//                Attachment attachment = new Attachment(null, Objects.requireNonNull(file).getOriginalFilename(), file.getContentType(), file.getSize(), null);
//                AttachmentContent attachmentContent = new AttachmentContent(null, file.getBytes(), attachment);
//                attachment.setAttachmentContent(attachmentContent);
                AttachmentContentModel attachmentContentModel = new AttachmentContentModel(null, Objects.requireNonNull(file).getBytes());
                AttachmentModel attachmentModel = new AttachmentModel(null, Objects.requireNonNull(file).getOriginalFilename(), file.getContentType(), file.getSize(), attachmentContentModel);
                AttachmentModel save = save(attachmentModel);
                attachmentModels.add(save);
            }
        }
        return attachmentModels;
    }

    @SneakyThrows
    public void downloadAttachmentByUserId(HttpServletResponse response, Long userId){
        Attachment attachment = repository.findByUserId(userId).orElseThrow();
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId()).orElseThrow();
        response.setContentType(attachment.getContentType());
        response.addHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
        FileCopyUtils.copy(attachmentContent.getContent(), response.getOutputStream());
    }

    @SneakyThrows
    public void getAttachmentsByPostId(HttpServletResponse response, Long postId) {
        List<Attachment> allByPostId = repository.findAllByPostId(postId);
        for (Attachment attachment : allByPostId) {
            AttachmentContent attachmentContent = attachment.getAttachmentContent();
            response.setContentType(attachment.getContentType());
            response.addHeader("Content-Disposition","attachment; filename=\""+ attachment.getName()+ "\"");
            FileCopyUtils.copy(attachmentContent.getContent(), response.getOutputStream());
        }
    }
}
