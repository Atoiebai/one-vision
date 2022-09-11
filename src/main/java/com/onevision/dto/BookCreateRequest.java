package com.onevision.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class BookCreateRequest {
    @NotNull(message = "title is required")
    @NotEmpty(message = "title should not be empty")
    private String title;
    @NotNull(message = "author is required")
    @NotEmpty(message = "author should not be null")
    private String author;
    private String description;
}
