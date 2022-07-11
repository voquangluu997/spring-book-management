package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddBookRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    private String image;
}
