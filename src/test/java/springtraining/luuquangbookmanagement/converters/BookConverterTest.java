package springtraining.luuquangbookmanagement.converters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.mocks.book.BookMock;
import springtraining.luuquangbookmanagement.repositories.entities.Book;

@ExtendWith(SpringExtension.class)
public class BookConverterTest {

    @InjectMocks
    private BookConverter converter;

    @Test
    public void test_convertUpdateBookRequestToBookEntity() {
        final UpdateBookRequestDTO dto = BookMock.createUpdateBookRequestDTO();
        final Book book = converter.convertUpdateBookDTOToBookEntity(dto);
    }

    @Test
    public void test_convertAddBookRequestToBookEntity() {
        final AddBookRequestDTO dto = BookMock.createAddBookRequestDTO();
        final Book book = converter.convertAddBookDTOToBookEntity(dto);
    }
}