package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.*;
import com.spring.onedayboot.book.entity.Book;
import com.spring.onedayboot.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    // 생성자 주입을 이용하여 BookRepository 객체를 주입받습니다.
    private final BookRepository bookRepository;

    /**
     * 책을 등록한다.
     * @param request 책 등록 정보를 담은 DTO
     * @return 등록된 책의 id
     */
    @Override
    @Transactional
    public Long createBook(BookCreateRequest request) {
        try {
            Book book = request.toEntity();
            Book savedBook = this.bookRepository.save(book);
            return savedBook.getBookId();
        } catch (DataAccessException e) {
            throw new RuntimeException("책 정보 저장에 실패했습니다.", e);
        }
    }

    /**
     * 책을 조회한다.
     * @param bookId 조회할 책의 id
     * @return 조회된 책 정보를 담은 DTO
     * @throws NoSuchElementException 조회된 책이 없을 때
     */
    @Override
    public BookReadResponse readBook(Long bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("책 정보를 찾을 수 없습니다."));
        return BookReadResponse.fromBook(book);
    }

    /**
     * 수정할 책을 조회한다.
     * @param bookId 수정할 책의 id
     * @return 수정할 책의 정보를 담은 DTO
     * @throws NoSuchElementException 수정할 책이 없을 때
     */
    @Override
    public BookEditResponse editBook(Long bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("책 정보를 찾을 수 없습니다."));
        return BookEditResponse.fromBook(book);
    }

    /**
     * 책 정보를 수정한다.
     * @param request 수정할 책 정보를 담은 DTO
     * @throws NoSuchElementException 수정할 책이 없을 때
     */
    @Override
    public void updateBook(BookUpdateRequest request) throws NoSuchElementException {
        Book book = this.bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NoSuchElementException("책 정보를 찾을 수 없습니다."));
        Book updatedBook = request.update(book);
        this.bookRepository.save(updatedBook);
    }

    /**
     * 책을 삭제한다.
     * @param bookId 삭제할 책의 id
     * @throws NoSuchElementException 삭제할 책이 없을 때
     */
    @Override
    public void deleteBook(Long bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("책 정보를 찾을 수 없습니다."));
        this.bookRepository.delete(book);
    }

    /**
     * 책을 검색한다.
     * @param title 검색할 책 제목
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @param direction 정렬 방향
     * @return 검색된 책 정보를 담은 DTO 리스트
     */
    @Override
    public List<BookListResponse> searchBooks(String title,
                                              Integer page,
                                              Integer size,
                                              Sort.Direction direction) {

        // 페이지 번호 기본값
        if (page == null || page < 0) {
            page = 0;
        // 사용자 UX를 고려한 페이지 번호 조정
        } else {
            page -= 1;
        }

        // 페이지 당 아이템 수 기본값
        if (size == null || size <= 0) {
            size = DEFAULT_PAGE_SIZE;
        }

        // 정렬 방향 기본값
        if (direction == null) {
            direction = Sort.Direction.ASC;
        }

        // createdAt 필드를 기준으로 정렬
        Sort sort = Sort.by(direction, "createdAt");

        // of 메서드를 사용하여 Pageable 객체를 생성
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> bookPage;
        if (title == null || title.isEmpty()) {
            bookPage = this.bookRepository.findAll(pageable);
        } else {
            bookPage = this.bookRepository.findByTitleContaining(title, pageable);
        }

        return bookPage.stream()
                .map(BookListResponse::fromBook)
                .collect(Collectors.toList());
    }
}
