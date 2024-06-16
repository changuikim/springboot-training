package com.spring.onedayboot.book.dto;

import com.spring.onedayboot.book.entity.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest {

    @NonNull
    @Positive
    private Integer bookId;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @Min(100)
    private Integer price;

    /**
     * Book 객체의 필드를 업데이트한 새 Book 객체를 반환한다.
     * @param book 업데이트할 Book 객체
     * return 업데이트된 새 Book 객체
     */
    public Book update(Book book) {
        return book.toBuilder()
                .bookId(book.getBookId())
                .title(this.title)
                .price(this.price)
                .createdAt(book.getCreatedAt())
                .build();
    }

}
