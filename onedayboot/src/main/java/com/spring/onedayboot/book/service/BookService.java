package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.BookCreateRequest;

public interface BookService {
    /**
     * 책을 등록한다.
     * @param bookCreateRequest 책 등록 정보를 담은 DTO
     * @return 등록된 책의 id
     */
    public Integer createBook(BookCreateRequest bookCreateRequest);

}
