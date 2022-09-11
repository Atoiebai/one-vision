package com.onevision.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BookCreatedResponse {
    private Long id;
    private String title;
    private String author;
    private String description;
}
