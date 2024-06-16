package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BookReadResponse {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime createdAt;

    /**
     * Book 객체를 BookReadResponse 객체로 변환한다.
     * @param book 변환할 Book 객체
     * @return 변환된 BookReadResponse 객체
     * @throws IllegalArgumentException Book 객체가 null 일 때
     */
    public static BookReadResponse fromBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book 객체는 null 일 수 없습니다.");
        }

        return BookReadResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
