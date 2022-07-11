package springtraining.luuquangbookmanagement.services.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import springtraining.luuquangbookmanagement.converter.BookConverter;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.exceptions.BookNotFoundException;
import springtraining.luuquangbookmanagement.mocks.book.BookMock;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.providers.UserProvider;
import springtraining.luuquangbookmanagement.repositories.BookRepository;
import springtraining.luuquangbookmanagement.repositories.UserRepository;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.repositories.entities.User;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsImpl;
import springtraining.luuquangbookmanagement.services.BookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProvider userProvider;

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookConverter converter;

    @Test
    public void test_getBookById_Success() {
        Book book = BookMock.createBook();
        when(bookRepository.findById(book.getId())).thenReturn(book);
        Book foundBook = bookService.getById(book.getId());
        assertEquals(foundBook, book);
        verify(bookRepository).findById(book.getId());
    }

    @Test
    public void test_getBookById_IdNotFound() {
        long incorrectId = 123;
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.getById(incorrectId);
        });
        assertEquals(exception.getMessage(), "Book ID " + incorrectId + " is not found.");
    }

    @Test
    public void test_addBook_Success() {
        AddBookRequestDTO bookDTO = BookMock.createAddBookRequestDTO();
        User user = UserMock.createUser();
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        when(converter.convertAddBookDTOToBookEntity(bookDTO)).thenReturn(BookMock.createBook());
        when(userRepository.findById(user.getId())).thenReturn(user);
        when(userProvider.getCurrentUser()).thenReturn(userDetails);
        assertDoesNotThrow(() -> {
            bookService.addBook(bookDTO);
        });
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void test_updateBook_Success() {
        Book book = BookMock.createBook();
        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
        when(bookRepository.findById(book.getId())).thenReturn(book);
        when(converter.convertUpdateBookDTOToBookEntity(bookDTO)).thenReturn(book);
        assertDoesNotThrow(() -> {
            bookService.updateBook(book.getId(), bookDTO);
        });
        verify(bookRepository).findById(book.getId());
    }

    @Test
    public void test_updateBook_IdNotFound() {
        long incorrectId = 123;
        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.updateBook(incorrectId, bookDTO);
        });
        assertEquals(exception.getMessage(), "Book ID " + incorrectId + " is not found.");
    }

    @Test
    public void test_getBooks_Success() {
        BookFilterDTO bookFilter = BookMock.createBookFilterDTO();
        Page<Book> bookPage = new PageImpl<>(BookMock.createBooks());
        when(bookRepository.search(anyString(), any(Pageable.class), anyString())).thenReturn(bookPage);

        final GetBooksResponseDTO getBooksResponseDTO = bookService.getBooks(bookFilter);
        assertEquals(getBooksResponseDTO.getTotalPages(), bookPage.getTotalPages());
        assertEquals(getBooksResponseDTO.getCurrentPage(), bookPage.getNumber());
        assertEquals(getBooksResponseDTO.getTotalItems(), bookPage.getTotalElements());
        assertEquals(getBooksResponseDTO.getBooks().size(), bookPage.getContent().size());
        assertArrayEquals(getBooksResponseDTO.getBooks().toArray(new Book[0]), bookPage.getContent().toArray(new Book[0]));
        verify(bookRepository).search(anyString(), any(Pageable.class), anyString());
    }

    @Test
    public void test_deleteBook_Success() {
        Book book = BookMock.createBook();
        when(bookRepository.findById(book.getId())).thenReturn(book);
        assertDoesNotThrow(() -> {
            bookService.deleteById(book.getId());
        });
        verify(bookRepository).findById(book.getId());
    }

    @Test
    public void test_deleteBook_IdNotFound() {
        long incorrectId = 123;
        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.updateBook(incorrectId, bookDTO);
        });
        assertEquals(exception.getMessage(), "Book ID " + incorrectId + " is not found.");
    }

}

