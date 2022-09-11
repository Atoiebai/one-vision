package com.onevision.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseBookResponse {
    private Long id;
    private String title;
    private String author;
    private String description;
}
