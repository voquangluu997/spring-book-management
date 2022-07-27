package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UpdateBookRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    private boolean enabled;
}
