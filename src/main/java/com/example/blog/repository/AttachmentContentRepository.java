package com.example.blog.repository;

import com.example.blog.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Long> {
    @Query(value = "select new AttachmentContent(ac.id,ac.content) from AttachmentContent ac join Attachment a on a.attachmentContent.id=ac.id where a.id=:attachment_id")
    Optional<AttachmentContent> findByAttachmentId(Long attachment_id);
}
