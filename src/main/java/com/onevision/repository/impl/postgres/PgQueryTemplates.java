package com.onevision.repository.impl.postgres;

public class PgQueryTemplates {

    private PgQueryTemplates() {}
        public static final String SELECT_ALL = "select * from book order by book.title desc";
        public static final String SELECT_BY_AUTHOR = "select * from book where author=:author";
        public static final String SAVE_BOOK = "insert into book (author, title , description) VALUES (:author, :title, :description)";
        public static final String SELECT_BY_TITLE_CONTAINS_CHAR = "select author , \n " + "SUM( length(lower(b.title)) - length(REPLACE(lower(b.title), :char_to_count , '' ))) as char_count \n " + "from book as b \n " + "where lower(b.title) like :char_to_like \n " + "GROUP BY b.author\n " + "order by char_count desc \n " + "limit 10;";
}
