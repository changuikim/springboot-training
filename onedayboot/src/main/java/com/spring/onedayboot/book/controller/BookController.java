package com.spring.onedayboot.book.controller;

import com.spring.onedayboot.book.dto.BookCreateRequest;
import com.spring.onedayboot.book.dto.BookEditResponse;
import com.spring.onedayboot.book.dto.BookReadResponse;
import com.spring.onedayboot.book.dto.BookUpdateRequest;
import com.spring.onedayboot.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    /**
     * 책 수정 페이지로 이동한다.
     * @param bookId 수정할 책의 id
     * @return 책 수정 페이지
     */
    @RequestMapping( method = RequestMethod.GET, value = "/book/edit/{bookId}")
    public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {
        ModelAndView mav = new ModelAndView();
        BookEditResponse response = this.bookService.editBook(bookId);
        mav.setViewName("book/edit");
        mav.addObject("book", response);
        return mav;
    }

    /**
     * 책 정보를 수정한다.
     * @param request 수정할 책 정보를 담은 DTO
     * @return 수정된 책 조회 페이지로 이동
     */
    @RequestMapping( method = RequestMethod.POST, value = "/book/edit/{bookId}")
    public ModelAndView update(@Validated BookUpdateRequest request, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining("\n"));
            return error422(errorMessage, String.format("/book/edit/%s", request.getBookId()));
        }
        this.bookService.updateBook(request);
        return new ModelAndView(String.format("redirect:/book/read/%s", request.getBookId()));
    }

    /**
     * 책을 삭제한다.
     * @param bookId 삭제할 책의 id
     * @return 책 목록 페이지로 이동
     */
    @RequestMapping( method = RequestMethod.POST, value = "/book/delete")
    public String delete(@RequestParam("bookId") Integer bookId) throws NoSuchElementException {
        System.out.println("bookId = " + bookId);
        this.bookService.deleteBook(bookId);
        return "redirect:/book/list";
    }

    /**
     * NoSuchElementException 예외 처리 핸들러
     * @param e NoSuchElementException 예외     *
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandler(NoSuchElementException e) {
        return error422(e.getMessage(), "/book/list");
    }

    /**
     * 422 에러 페이지를 반환한다.
     * @param message 에러 메시지
     * @param location 이전 페이지로 돌아가기 위한 경로
     * @return 422 에러 페이지
     */
    private ModelAndView error422(String message, String location) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("common/error/422");
        mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("message", message);
        mav.addObject("location", location);
        return mav;
    }

}
