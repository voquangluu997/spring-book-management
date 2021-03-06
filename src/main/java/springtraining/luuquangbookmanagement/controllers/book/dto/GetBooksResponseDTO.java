package springtraining.luuquangbookmanagement.controllers.book.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import springtraining.luuquangbookmanagement.pagination.dto.PaginationResponseDTO;
import springtraining.luuquangbookmanagement.repositories.entities.Book;

import java.util.List;

@Getter
@SuperBuilder
public class GetBooksResponseDTO extends PaginationResponseDTO {
    private List<Book> books;

}
