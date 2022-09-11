package com.onevision.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksFilterRequest {
    @NotEmpty
    @NotNull
    @Size(max = 1, min = 1)
    private String charFromTitle;
}
