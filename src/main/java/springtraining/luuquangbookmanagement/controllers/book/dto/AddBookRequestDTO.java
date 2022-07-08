package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class AddBookRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    private String image;
}
