package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.Data;

@Data
public class UpdateBookRequestDTO extends BookDTO {
    private boolean enable;
}
