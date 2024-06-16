package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.dto.BookReadResponse;

import java.util.NoSuchElementException;

public interface BookService {
    /**
     * 책을 등록한다.
     * @param request 책 등록 정보를 담은 DTO
     * @return 등록된 책의 id
     */
    public Integer createBook(BookCreateRequest request);

    /**
     * 책을 조회한다.
     * @param bookId 조회할 책의 id
     * @return 조회된 책 정보를 담은 DTO
     * @throws NoSuchElementException 조회된 책이 없을 때
     */
    public BookReadResponse readBook(Integer bookId) throws NoSuchElementException;
}
