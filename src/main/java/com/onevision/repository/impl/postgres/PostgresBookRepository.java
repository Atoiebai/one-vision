package com.onevision.repository.impl.postgres;


import com.onevision.entity.Book;
import com.onevision.exception.BookAlreadyExistsException;
import com.onevision.exception.BookNotSavedException;
import com.onevision.mapper.BookRowMapper;
import com.onevision.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.onevision.mapper.BookRowMapper.*;
import static com.onevision.repository.impl.postgres.PgQueryTemplates.*;


@AllArgsConstructor
@Repository
@Primary
@Slf4j
public class PostgresBookRepository implements BookRepository {

    private final BookRowMapper bookRowmapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Book> getBookList() {
        try {
            return jdbcTemplate.query(SELECT_ALL, bookRowmapper);
        } catch (Exception e) {
            log.error("Error while getting books from database. ErrorMessage: {} , Cause: {}", e, e.getMessage(),e.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Book addBook(Book book) {
        if(bookExists(book)) throw new BookAlreadyExistsException("Book with title: " + book.getTitle() + " and author: " + book.getAuthor() + " already exists");

        var params = new MapSqlParameterSource()
            .addValue(AUTHOR, book.getAuthor())
            .addValue(TITLE, book.getTitle())
            .addValue(DESCRIPTION, book.getDescription());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(SAVE_BOOK, params, keyHolder);
            log.info("Book saved successfully. Author: {} , Title: {}" , book.getAuthor(), book.getTitle());
        } catch (Exception e) {
            log.error("Error saving book. With title: {} , Author: {} \n. Cause {}", book.getTitle(), book.getAuthor() , e.getCause());
            throw new BookNotSavedException("Error while trying to save book message: " + e.getMessage() , e.getCause());
        }
        return book;
    }

    @Override
    public List<Book> getBookListByAuthor() {
        try {
            return jdbcTemplate.query(
                SELECT_ALL,
                bookRowmapper);
        } catch (Exception e) {
            log.error("getBookListByAuthor() thrown exception while processing request: " + e.getMessage(), e.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Map<String, Integer> getBooksByTitleContains(String charToFind) {
        Map<String, Integer> books = new LinkedHashMap<>();

        var params = new MapSqlParameterSource().addValue("char_to_count", charToFind.toLowerCase(Locale.ROOT)).addValue("char_to_like", "%" + charToFind.toLowerCase(Locale.ROOT)+ "%");

        try {
            jdbcTemplate.query(SELECT_BY_TITLE_CONTAINS_CHAR, params, (rs, rowNum) -> {
                do books.put(rs.getString(AUTHOR), rs.getInt("char_count")); while (rs.next());
                return "";
            });

            return books;
        } catch (Exception e) {
            log.error("Exception while trying to get books", e.getCause());
            return Collections.emptyMap();
        }

    }

    private boolean bookExists(Book book) {
        Integer result =  jdbcTemplate.queryForObject("select count(*) from book where author = :author and title = :title" ,
            new MapSqlParameterSource().addValue("author", book.getAuthor()).addValue("title", book.getTitle()), Integer.class);
        return result != null && result != 0;
    }


}
