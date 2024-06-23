package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookListResponse {

    private Long bookId;
    private String title;

    /**
     * Book 객체를 BookListResponse 객체로 변환한다.
     * @param book 변환할 Book 객체
     * @return 변환된 BookListResponse 객체
     */
    public static BookListResponse fromBook(Book book) {
        return BookListResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .build();
    }
}
