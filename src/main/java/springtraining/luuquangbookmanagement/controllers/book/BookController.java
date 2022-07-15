package springtraining.luuquangbookmanagement.controllers.book;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.services.book.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void add(@Valid @RequestBody AddBookRequestDTO bookRequest) {
        bookService.add(bookRequest);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable int id) {
        return bookService.getById(id);
    }

    @PostMapping("filter")
    public GetBooksResponseDTO getBooks(@RequestBody BookFilterDTO bookFilterDTO) {
        return bookService.getBooks(bookFilterDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void update(@PathVariable int id,
                       @RequestBody UpdateBookRequestDTO bookRequest
    ) {
        bookService.updateBook(id, bookRequest);
    }

}
