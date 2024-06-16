package com.spring.onedayboot.book.controller;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/book/create")
    public String create() {
        return "book/create";
    }

    @RequestMapping( method = RequestMethod.POST, value = "/book/create")
    public String create(BookCreateRequest request) {
        Integer bookId = this.bookService.createBook(request);
        return String.format("redirect:/book/read/%d", bookId);
    }
}
