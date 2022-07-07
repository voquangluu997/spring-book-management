package springtraining.luuquangbookmanagement.controllers.book.dto;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
public class BookDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    private String image;
}
