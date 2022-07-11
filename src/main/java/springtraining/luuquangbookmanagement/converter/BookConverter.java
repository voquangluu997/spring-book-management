package springtraining.luuquangbookmanagement.converter;

import org.springframework.stereotype.Service;
import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.repositories.entities.User;

@Service
public class BookConverter {

    public Book convertAddBookDTOToBookEntity(AddBookRequestDTO bookDTO) {
        final Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setImage(bookDTO.getImage());
        book.setTitle(bookDTO.getTitle());
        return book;
    }

    public Book convertUpdateBookDTOToBookEntity(UpdateBookRequestDTO bookDTO) {
        final Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setImage(bookDTO.getImage());
        book.setTitle(bookDTO.getTitle());
        book.setEnabled(bookDTO.isEnabled());
        return book;
    }



}
