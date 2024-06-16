package com.spring.onedayboot.book.controller;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.dto.BookReadResponse;
import com.spring.onedayboot.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 책 등록 페이지로 이동한다.
     * @return 책 등록 페이지
     */
    @RequestMapping( method = RequestMethod.GET, value = "/book/create")
    public String create() {
        return "book/create";
    }

    /**
     * 책을 등록한다.
     * @param request 책 등록 정보를 담은 DTO
     * @return 등록된 책 조회 페이지로 이동
     */
    @RequestMapping( method = RequestMethod.POST, value = "/book/create")
    public String create(BookCreateRequest request) {
        Integer bookId = this.bookService.createBook(request);
        return String.format("redirect:/book/read/%d", bookId);
    }

    /**
     * 책 조회 페이지로 이동한다.
     * @param bookId 조회할 책의 id
     * @return 책 조회 페이지
     */
    @RequestMapping( method = RequestMethod.GET, value = "/book/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId) {
        ModelAndView mav = new ModelAndView();
        try {
            BookReadResponse response = this.bookService.readBook(bookId);
            mav.setViewName("book/read");
            mav.addObject("book", response);
        } catch (NoSuchElementException e) {
            mav.setViewName("common/error/422");
            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            mav.addObject("message", "책 정보를 찾을 수 없습니다.");
            mav.addObject("location", "/book");
        }
        return mav;
    }
}
