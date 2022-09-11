package com.onevision.controller;

import com.onevision.dto.*;
import com.onevision.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.onevision.dto.ResponseData.response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<ResponseData<BookCreatedResponse>> addBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return response(bookService.addBook(bookCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseData<List<BaseBookResponse>>> getAllBooks() {
        return response(bookService.getAllBooks() , HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<ResponseData<Map<String, List<BaseBookResponse>>>> getBooksByAuthor() {
        return response(bookService.getBooksByAuthor(), HttpStatus.OK);
    }

    @PostMapping("/books/search")
    public ResponseEntity<ResponseData<Map<String, Integer>>> getBooks(@RequestBody @Valid BooksFilterRequest booksFilter) {
        // TODO: 11/09/22  search by multiple params (currently not supported)
        return response(bookService.booksByTitleContains(booksFilter.getCharFromTitle().toString()), HttpStatus.OK);
    }


}
