package com.example.blog.repository;

import com.example.blog.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

    @Query(value = "select new Attachment(a.id,a.name,a.contentType,a.size,a.attachmentContent)from Attachment a join users u on u.attachment.id=a.id where u.id=:user_id")
    Optional<Attachment> findByUserId(Long user_id);

    @Query(value = "select case when(count(u)>0) then true else false end from users u join Attachment a on u.attachment.id=a.id where u.id=:user_id")
    boolean existsByUserId(Long user_id);

    @Query(nativeQuery = true, value = "select a.id, a.name, a.size, a.content_type, a.attachment_content_id from post p inner join post_attachments pa on p.id=pa.post_id inner join attachment a on pa.attachments_id=a.id inner join attachment_content ac on a.attachment_content_id=ac.id where p.id=:post_id")
    List<Attachment> findAllByPostId(Long post_id);

//    @Query(value = "select new Attachment(a.id,a.name,a.contentType,a.size,a.attachmentContent,a.content) from Attachment a join Content c on a.content.id=c.id join Post p on c.post.id=p.id where p.postType=:postType and p.user.id=:userId")
//    Optional<Attachment> findByPostTypeAndUserId(PostType postType, Long userId);
}
