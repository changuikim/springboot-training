package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.CommentCreateRequest;
import com.spring.onedayboot.book.dto.CommentReadResponse;
import com.spring.onedayboot.book.entity.Book;
import com.spring.onedayboot.book.entity.Comment;
import com.spring.onedayboot.book.repository.BookRepository;
import com.spring.onedayboot.book.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    /**
     * 코멘트를 등록한다.
     * @param request 코멘트 등록 정보를 담은 DTO
     * @return 등록된 코멘트의 id
     */
    @Override
    public CommentReadResponse createComment(CommentCreateRequest request) {
        Book book = this.bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("책 정보를 찾을 수 없습니다."));
        Comment comment = request.toEntity(book);
        comment = this.commentRepository.save(comment);
        return CommentReadResponse.fromComment(comment);
    }
}
