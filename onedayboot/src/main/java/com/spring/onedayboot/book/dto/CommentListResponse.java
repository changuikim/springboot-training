package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentListResponse {
    private Long commentId;
    private String comment;
    private Integer page;
    private LocalDateTime createdAt;
    private String displayComment;

    /**
     * Comment 객체를 CommentListResponse 객체로 변환한다.
     * @param comment 변환할 Comment 객체
     * @return 변환된 CommentListResponse 객체
     */
    public static CommentListResponse fromComment(Comment comment) {
        return CommentListResponse.builder()
                .commentId(comment.getCommentId())
                .comment(comment.getComment())
                .page(comment.getPage())
                .createdAt(comment.getCreatedAt())
                .displayComment(comment.getPage() == null ? "" : "(p." + comment.getPage() + ".) " + comment.getComment())
                .build();
    }
}