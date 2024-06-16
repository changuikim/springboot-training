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

    public Book toEntity() {
        return Book.builder()
                .title(this.title)
                .price(this.price)
                .build();
    }
}
