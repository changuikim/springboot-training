package com.spring.onedayboot.book.service;

import com.spring.onedayboot.book.dto.CommentCreateRequest;
import com.spring.onedayboot.book.dto.CommentReadResponse;

public interface CommentService {

    /**
     * 코멘트를 등록한다.
     * @param request 코멘트 등록 정보를 담은 DTO
     * @return 등록된 코멘트의 id
     */
    public CommentReadResponse createComment(CommentCreateRequest request);
}
