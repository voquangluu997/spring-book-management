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

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public GetBooksResponseDTO getBooks(@RequestParam String search, @RequestParam int page, @RequestParam int limit, @RequestParam String orderBy) {
        final BookFilterDTO dto = BookFilterDTO.builder().page(page).search(search).limit(limit).orderBy(orderBy)
                .build();
        return bookService.getBooks(dto);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) throws NotFoundException {
        return bookService.getById(id);
    }

    @Secured("ADMIN")
    @PostMapping
    public Book addBook(@RequestBody AddBookRequestDTO bookRequest) {
        System.out.println(bookRequest);
        return bookService.addBook(bookRequest);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) throws NotFoundException {
        return bookService.deleteById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public Book update(@PathVariable long id, @RequestBody UpdateBookRequestDTO bookRequest) throws NotFoundException {
        return bookService.update(id, bookRequest);
    }

}
