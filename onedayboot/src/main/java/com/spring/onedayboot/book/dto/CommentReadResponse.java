package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentReadResponse {

    private Long commentId;
    private Long bookId;
    private String comment;
    private Integer page;

    /**
     * Comment 객체를 CommentReadResponse 객체로 변환한다.
     * @param comment 변환할 Comment 객체
     * @return 변환된 CommentReadResponse 객체
     */
    public static CommentReadResponse fromComment(Comment comment) {
        return CommentReadResponse.builder()
                .commentId(comment.getCommentId())
                .bookId(comment.getBook().getBookId())
                .comment(comment.getComment())
                .page(comment.getPage())
                .build();
    }
}
