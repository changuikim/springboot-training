package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.dto.BookReadResponse;
import com.spring.onedayboot.book.entity.Book;
import com.spring.onedayboot.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    // 생성자 주입을 이용하여 BookRepository 객체를 주입받습니다.
    private final BookRepository bookRepository;

    /**
     * 책을 등록한다.
     * @param request 책 등록 정보를 담은 DTO
     * @return 등록된 책의 id
     */
    @Override
    @Transactional
    public Integer createBook(BookCreateRequest request) {
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
    public BookReadResponse readBook(Integer bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("책 정보를 찾을 수 없습니다."));
        return BookReadResponse.fromBook(book);
    }
}
