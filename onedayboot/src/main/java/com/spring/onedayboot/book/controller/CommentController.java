package com.spring.onedayboot.book.controller;

import com.spring.onedayboot.book.dto.CommentCreateRequest;
import com.spring.onedayboot.book.dto.CommentReadResponse;
import com.spring.onedayboot.book.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<CommentReadResponse> create(@RequestBody CommentCreateRequest request) {
        CommentReadResponse response = this.commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
