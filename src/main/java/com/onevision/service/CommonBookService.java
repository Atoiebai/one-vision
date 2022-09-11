package com.onevision.service;


import com.onevision.dto.BaseBookResponse;
import com.onevision.dto.BookCreateRequest;
import com.onevision.dto.BookCreatedResponse;
import com.onevision.entity.Book;
import com.onevision.exception.BookAlreadyExistsException;
import com.onevision.mapper.BookMapper;
import com.onevision.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CommonBookService implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookCreatedResponse addBook(BookCreateRequest bookCreateRequest) {
         Book book = bookMapper.toEntity(bookCreateRequest);

         try {
             bookRepository.addBook(book);
             return bookMapper.bookCreatedResponse(book);
         } catch (BookAlreadyExistsException e) {
             throw e;
         }
         catch (Exception e) {
             log.error("An error occurred while saving the book to the database" , e.getCause() );
             throw new RuntimeException("An error occurred while saving the book to the database" , e.getCause());
         }
    }

    @Override
    public List<BaseBookResponse> getAllBooks() {
         List<Book> books = bookRepository.getBookList();

         if(books == null) return List.of();

         return books.stream().map(bookMapper::toBaseResponse).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<BaseBookResponse>> getBooksByAuthor() {
       var books = bookRepository.getBookListByAuthor();

        if(books.isEmpty()) return Collections.emptyMap();

        return books
            .stream()
            .collect(Collectors.groupingBy(Book::getAuthor, Collectors.mapping(bookMapper::toBaseResponse, Collectors.toList())));
    }

    @Override
    public Map<String, Integer> booksByTitleContains(String authorName) {
        return bookRepository.getBooksByTitleContains(authorName);
    }


}
