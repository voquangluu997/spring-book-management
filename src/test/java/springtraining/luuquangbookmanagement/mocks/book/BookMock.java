package springtraining.luuquangbookmanagement.mocks.book;

import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.repositories.entities.Book;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class BookMock {
    public static Book createBook() {
        return Book.builder()
                .id(1)
                .title(UUID.randomUUID().toString())
                .author(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .enabled(true)
                .createdAt(null)
                .updatedAt(null)
                .user(UserMock.createUser())
                .build();
    }

    public static List<Book> createBooks() {
        return createObjects(BookMock::createBook);
    }

    public static <T> List<T> createObjects(final Supplier<T> creator) {
        return IntStream.range(0, new Random().nextInt(1, 10))
                .mapToObj(x -> creator.get())
                .toList();
    }

    public static AddBookRequestDTO createAddBookRequestDTO() {
        return AddBookRequestDTO.builder()
                .title("title")
                .author("author")
                .description("desc")
//                .image("image")
                .build();
    }

    public static UpdateBookRequestDTO createUpdateBookRequestDTO() {
        return UpdateBookRequestDTO.builder()
                .title("title")
                .author("author")
                .description("desc")
//                .image("image")
                .enabled(true)
                .build();
    }

    public static BookFilterDTO createBookFilterDTO() {
        return BookFilterDTO.builder()
                .page(0)
                .limit(10)
                .search("search")
                .orderBy("")
                .build();
    }

    public static GetBooksResponseDTO createGetBooksResponseDTO() {
        List<Book> books = createBooks();
        return GetBooksResponseDTO.builder()
                .currentPage(0)
                .totalPages(2)
                .totalItems(1)
                .books(books)
                .build();
    }


}
