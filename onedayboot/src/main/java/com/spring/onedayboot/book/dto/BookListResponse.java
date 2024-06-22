package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookListResponse {

    private final Integer bookId;
    private final String title;

    public static BookListResponse fromBook(Book book) {
        return BookListResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .build();
    }
}
