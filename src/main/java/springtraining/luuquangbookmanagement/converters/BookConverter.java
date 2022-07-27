package springtraining.luuquangbookmanagement.converters;

import org.springframework.stereotype.Service;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.repositories.entities.Book;

@Service
public class BookConverter {

    public Book convertAddBookDTOToBookEntity(AddBookRequestDTO bookDTO) {
        final Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setTitle(bookDTO.getTitle());
        return book;
    }

    public Book convertUpdateBookDTOToBookEntity(Book book, UpdateBookRequestDTO bookDTO) {
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setTitle(bookDTO.getTitle());
        book.setEnabled(bookDTO.isEnabled());
        return book;
    }
}
