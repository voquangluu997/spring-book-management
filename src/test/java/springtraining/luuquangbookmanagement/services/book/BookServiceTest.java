package springtraining.luuquangbookmanagement.services.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import springtraining.luuquangbookmanagement.exceptions.NotFoundException;
import springtraining.luuquangbookmanagement.mocks.book.BookMock;
import springtraining.luuquangbookmanagement.repositories.BookRepository;
import springtraining.luuquangbookmanagement.repositories.UserRepository;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.services.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Test book by id success")
    public void getBookByIdSuccess() {
        Book book = BookMock.createBook();
        when(bookRepository.findById(book.getId())).thenReturn(book);
        Book foundBook = bookService.getById(book.getId());
        assertEquals(foundBook, book);
        verify(bookRepository).findById(book.getId());
    }

    @Test
    @DisplayName("Test book by id failed because book not found")
    public void getBookByIdNotFound() {
        Book book = BookMock.createBook();
        long idToTest = 123;
        when(bookRepository.findById(idToTest)).thenThrow(new NotFoundException());
        Book foundBook = bookService.getById(idToTest);
        assertEquals(foundBook, book);
        verify(bookRepository).findById(book.getId());
    }

    @Test
    @DisplayName("Test book by id success")
    public void addBookSuccess() {
        Book book = BookMock.createBook();
//        when(bookRepository.findById(333)).thenReturn(book);
        Book foundBook = bookService.getById(book.getId());
//        assertEquals(foundBook, book);
//        verify(bookRepository).findById(book.getId());
    }


}
