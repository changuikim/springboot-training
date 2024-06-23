package com.spring.onedayboot.book.repository;

import com.spring.onedayboot.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
    Page<Book> findByTitleContaining(String title, Pageable pageable);
}
