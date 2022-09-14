package com.example.blog.controller;

import com.example.blog.payload.AttachmentModel;
import com.example.blog.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public List<AttachmentModel> addAttachment(MultipartRequest multipartRequest){
        return attachmentService.addAttachment(multipartRequest);
    }

    @GetMapping("/post")
    public void getAttachmentsByPostId(HttpServletResponse response, @RequestParam Long postId){
        attachmentService.getAttachmentsByPostId(response, postId);
    }
}
