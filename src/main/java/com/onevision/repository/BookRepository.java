package com.onevision.repository;


import com.onevision.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookRepository {


    Book addBook(Book book);

    List<Book> getBookList();

    List<Book> getBookListByAuthor();

    Map<String, Integer> getBooksByTitleContains(String authorName);

}
