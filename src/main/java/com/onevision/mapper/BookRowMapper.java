package com.onevision.mapper;

import com.onevision.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {

   public static final String TABLE_NAME = "book";
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
            .id(rs.getLong(ID))
            .author(rs.getString(AUTHOR))
            .title(rs.getString(TITLE))
            .description(rs.getString(DESCRIPTION))
            .build();
    }
}
