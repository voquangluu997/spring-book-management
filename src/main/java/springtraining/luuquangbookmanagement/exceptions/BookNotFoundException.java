package springtraining.luuquangbookmanagement.exceptions;

import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(long id) {
        super("Book ID " + id + " is not found.");
    }
}
