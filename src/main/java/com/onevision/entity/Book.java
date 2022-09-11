package com.onevision.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Book {

    private Long id;
    private String title;
    private String author;
    private String description;

}
