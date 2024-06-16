package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {

    @NonNull
    private String title;

    @NonNull
    private Integer price;

    /**
     * BookCreateRequest 객체를 Book 객체로 변환한다.
     * @return 변환된 Book 객체
     */
    public Book toEntity() {
        return Book.builder()
                .title(this.title)
                .price(this.price)
                .build();
    }
}
