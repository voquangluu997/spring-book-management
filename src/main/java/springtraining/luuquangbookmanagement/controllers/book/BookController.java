package springtraining.luuquangbookmanagement.controllers.book;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.exceptions.NotFoundException;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.services.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) throws NotFoundException {
        return bookService.getById(id);
    }

    @Secured("ADMIN")
    @PostMapping
    public void addBook(@Valid @RequestBody AddBookRequestDTO bookRequest) {
        bookService.addBook(bookRequest);
    }


    @PostMapping("getFilter")
    public GetBooksResponseDTO getBooks(@RequestBody BookFilterDTO bookFilterDTO) {
        return bookService.getBooks(bookFilterDTO);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody UpdateBookRequestDTO bookRequest) {
        bookService.update(id, bookRequest);
    }

}
