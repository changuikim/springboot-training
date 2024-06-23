package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import com.spring.onedayboot.book.entity.Comment;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    @NonNull
    @Positive
    private Long bookId;

    @NonNull
    private String comment;

    private Integer page;

    public Comment toEntity(Book book) {
        return Comment.builder()
                .book(book)
                .comment(this.comment)
                .page(this.page)
                .build();
    }
}
