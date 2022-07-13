package springtraining.luuquangbookmanagement.controllers.book;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ADMIN")
    @PostMapping
    public void add(@Valid @RequestBody AddBookRequestDTO bookRequest) {
        bookService.add(bookRequest);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) {
        return bookService.getById(id);
    }

    @PostMapping("filter")
    public GetBooksResponseDTO getBooks(@RequestBody BookFilterDTO bookFilterDTO) {
        return bookService.getBooks(bookFilterDTO);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public void update(@PathVariable long id,
    @RequestBody UpdateBookRequestDTO bookRequest
        ) {
        bookService.updateBook(id, bookRequest);
    }

}
