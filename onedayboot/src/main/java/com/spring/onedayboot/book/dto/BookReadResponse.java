package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class BookReadResponse {
    private Long bookId;
    private String title;
    private Long price;
    private LocalDateTime createdAt;
    private List<CommentListResponse> commentList;

    /**
     * Book 객체를 BookReadResponse 객체로 변환한다.
     * @param book 변환할 Book 객체
     * @return 변환된 BookReadResponse 객체
     */
    public static BookReadResponse fromBook(Book book) {
        return BookReadResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .createdAt(book.getCreatedAt())
                .commentList(book.getCommentList().stream().map(comment -> CommentListResponse.fromComment(comment)).toList())
                .build();
    }
}
