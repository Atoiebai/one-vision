package com.onevision.service;

import com.onevision.dto.BaseBookResponse;
import com.onevision.dto.BookCreateRequest;
import com.onevision.dto.BookCreatedResponse;

import java.util.List;
import java.util.Map;

public interface BookService {

    BookCreatedResponse addBook(BookCreateRequest bookCreateRequest);


    List<BaseBookResponse> getAllBooks();

    Map<String, List<BaseBookResponse>> getBooksByAuthor();

    Map<String , Integer> booksByTitleContains(String authorName);
}
