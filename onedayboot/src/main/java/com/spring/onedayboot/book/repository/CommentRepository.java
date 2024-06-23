package com.spring.onedayboot.book.repository;

import com.spring.onedayboot.book.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
