package springtraining.luuquangbookmanagement.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.converters.BookConverter;
import springtraining.luuquangbookmanagement.exceptions.BookNotFoundException;
import springtraining.luuquangbookmanagement.exceptions.NotFoundException;
import springtraining.luuquangbookmanagement.providers.UserProvider;
import springtraining.luuquangbookmanagement.repositories.BookRepository;
import springtraining.luuquangbookmanagement.repositories.UserRepository;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.repositories.entities.User;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsImpl;

import java.util.Date;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private BookConverter converter;

    public GetBooksResponseDTO getBooks(BookFilterDTO bookFilter) {
        final int page = bookFilter.getPage();
        final int limit = bookFilter.getLimit();
        Pageable paging = PageRequest.of(page, limit);
        Page<Book> bookPage = bookRepository.search(
                "%" + bookFilter.getSearch() + "%",
                paging,
                bookFilter.getOrderBy());
        return GetBooksResponseDTO.builder()
                .books(bookPage.getContent())
                .currentPage(bookPage.getNumber())
                .totalItems(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .build();
    }

    public Book getById(long id) throws
            NotFoundException {
        Book book = bookRepository.findById(id);
        if (book != null) {
            return book;
        }
        throw new BookNotFoundException(id);
    }

    public void add(AddBookRequestDTO bookRequest) {
        UserDetailsImpl userDetails = userProvider.getCurrentUser();
        User user = userRepository.findById(userDetails.getId());
        Book book = converter.convertAddBookDTOToBookEntity(bookRequest);
        book.setUser(user);
        book.setCreatedAt(new Date());
        bookRepository.save(book);
    }

    public void deleteById(long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public void updateBook(long id, UpdateBookRequestDTO bookRequest) {
        Book foundBook = bookRepository.findById(id);
        if (foundBook == null) {
            throw new BookNotFoundException(id);
        }
        Book book = converter.convertUpdateBookDTOToBookEntity(bookRequest);
        book.setUpdatedAt(new Date());
        bookRepository.save(book);
    }
}
