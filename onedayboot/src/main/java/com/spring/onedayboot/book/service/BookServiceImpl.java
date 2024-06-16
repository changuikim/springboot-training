package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.entity.Book;
import com.spring.onedayboot.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    // 생성자 주입을 이용하여 BookRepository 객체를 주입받습니다.
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Integer createBook(BookCreateRequest request) {
        try {
            // BookCreateRequest 객체를 이용하여 Book 객체를 생성합니다.
            Book book = request.toEntity();
        System.out.println("book: " + book);
            // JPA의 엔티티 매니저를 호출하여 Book 객체를 영속성 컨텍스트에 저장합니다.
            Book savedBook = this.bookRepository.save(book);
            // 저장된 Book 객체의 id를 반환합니다.
            return savedBook.getBookId();
        } catch (DataAccessException e) {
            throw new RuntimeException("책 정보 저장에 실패했습니다.", e);
        }
    }
}
