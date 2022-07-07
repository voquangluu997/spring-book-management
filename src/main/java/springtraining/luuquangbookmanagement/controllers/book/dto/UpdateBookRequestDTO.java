package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateBookRequestDTO extends BookDTO {
    private boolean enabled;
}
