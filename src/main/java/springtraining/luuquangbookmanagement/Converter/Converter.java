package springtraining.luuquangbookmanagement.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.repositories.entities.Book;

@Service
public class Converter {

    @Autowired
    private ModelMapper modelMapper;

    public Book convertAddBookDTOToBookEntity(AddBookRequestDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public Book convertUpdateBookDTOToBookEntity(UpdateBookRequestDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
