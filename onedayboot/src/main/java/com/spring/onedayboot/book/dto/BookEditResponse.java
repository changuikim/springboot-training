package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BookEditResponse {
    private Long bookId;
    private String title;
    private Long price;
    private LocalDateTime createdAt;

    /**
     * Book 객체를 BookEditResponse 객체로 변환한다.
     * @param book 변환할 Book 객체
     * @return 변환된 BookEditResponse 객체
     */
    public static BookEditResponse fromBook(Book book) {
        return BookEditResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
