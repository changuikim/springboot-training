package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;

public interface BookService {
    /**
     * 책을 등록한다.
     * @param request 책 등록 정보를 담은 DTO
     * @return 등록된 책의 id
     */
    public Long createBook(BookCreateRequest request);

    /**
     * 책을 조회한다.
     * @param bookId 조회할 책의 id
     * @return 조회된 책 정보를 담은 DTO
     * @throws NoSuchElementException 조회된 책이 없을 때
     */
    public BookReadResponse readBook(Long bookId) throws NoSuchElementException;

    /**
     * 수정할 책을 조회한다.
     * @param bookId 수정할 책의 id
     * @return 수정할 책의 정보를 담은 DTO
     * @throws NoSuchElementException 수정할 책이 없을 때
     */
    public BookEditResponse editBook(Long bookId) throws NoSuchElementException;

    /**
     * 책 정보를 수정한다.
     * @param request 수정할 책 정보를 담은 DTO
     * @throws NoSuchElementException 수정할 책이 없을 때
     */
    public void updateBook(BookUpdateRequest request) throws NoSuchElementException;

    /**
     * 책을 삭제한다.
     * @param bookId 삭제할 책의 id
     * @throws NoSuchElementException 삭제할 책이 없을 때
     */
    public void deleteBook(Long bookId) throws NoSuchElementException;

    /**
     * 책을 검색한다.
     * @param title 검색할 책 제목
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @param direction 정렬 방향
     * @return 검색된 책 정보를 담은 DTO 리스트
     */
    public List<BookListResponse> searchBooks(String title, Integer page, Integer size, Sort.Direction direction);

}
