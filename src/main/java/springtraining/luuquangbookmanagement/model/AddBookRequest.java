package springtraining.luuquangbookmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class AddBookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

}