package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import springtraining.luuquangbookmanagement.pagination.dto.PaginationFilterDTO;

@SuperBuilder
@AllArgsConstructor
public class BookFilterDTO extends PaginationFilterDTO {

}
