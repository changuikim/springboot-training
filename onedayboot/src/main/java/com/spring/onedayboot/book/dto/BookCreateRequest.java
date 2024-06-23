package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {

    @NotBlank
    private String title;

    @Min(100)
    private Long price;

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
