package com.example.blog.service;

import com.example.blog.component.UserDataLoader;
import com.example.blog.entity.Attachment;
import com.example.blog.entity.AttachmentContent;
import com.example.blog.entity.User;
import com.example.blog.mapper.AttachmentMapper;
import com.example.blog.mapper.UserMapper;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.AttachmentModel;
import com.example.blog.payload.UserModel;
import com.example.blog.repository.AttachmentContentRepository;
import com.example.blog.repository.AttachmentRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.template.GenericService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService extends GenericService<User, UserModel, UserRepository, UserMapper> {
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    AttachmentMapper attachmentMapper;
    @Autowired
    UserDataLoader userDataLoader;

    public UserService(UserMapper mapper, UserRepository repository) {
        super(mapper, repository);
    }

    public UserModel getUserById() {
        userDataLoader.run();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapper.entityToModel(currentUser);
    }

    public UserModel update(UserModel userModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return update(userModel, currentUser.getId());
    }

    public ApiResponse deleteUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return deleteById(currentUser.getId());
    }

    @SneakyThrows
    public void downloadProfilePhoto(HttpServletResponse response, Long userId) {
        attachmentService.downloadAttachmentByUserId(response, userId);
    }

    public AttachmentModel uploadProfilePhoto(MultipartRequest multipartRequest) {
        List<AttachmentModel> attachmentModels = attachmentService.addAttachment(multipartRequest);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setAttachment(attachmentMapper.modelToEntity(attachmentModels.get(0)));
        repository.save(user);
        return attachmentModels.get(0);
    }
}
