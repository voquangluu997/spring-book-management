package springtraining.luuquangbookmanagement.controllers.book;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springtraining.luuquangbookmanagement.controllers.book.dto.BookFilterDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.GetBooksResponseDTO;
import springtraining.luuquangbookmanagement.exceptions.BookNotFoundException;
import springtraining.luuquangbookmanagement.mocks.book.BookMock;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.services.book.BookService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser()
    public void test_getBookById_Found() throws Exception {
        final Book book = BookMock.createBook();
        when(bookService.getById(book.getId())).thenReturn(book);
        mvc.perform(MockMvcRequestBuilders.get("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
    }

    @Test
    @WithMockUser()
    public void test_getBookFilter_Success() throws Exception {
        GetBooksResponseDTO getBooksResponseDTO = BookMock.createGetBooksResponseDTO();
        BookFilterDTO bookFilterDTO = BookMock.createBookFilterDTO();
        when(bookService.getBooks(any(BookFilterDTO.class))).thenReturn(getBooksResponseDTO);
        Gson gson = new Gson();
        String json = gson.toJson(bookFilterDTO);
        mvc.perform(MockMvcRequestBuilders.post("/books/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", Matchers.equalTo(getBooksResponseDTO.getTotalPages())))
                .andExpect(jsonPath("$.currentPage", Matchers.equalTo(getBooksResponseDTO.getCurrentPage())));
        verify(bookService).getBooks(any(BookFilterDTO.class));
    }

    @Test
    @WithMockUser()
    public void test_getBookById_NotFound() throws Exception {
        final Book book = BookMock.createBook();
        when(bookService.getById(book.getId())).thenThrow(BookNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders.get("/books/" + (book.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(bookService).getById(book.getId());
    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void test_addBook_Success() throws Exception {
//        AddBookRequestDTO bookDTO = BookMock.createAddBookRequestDTO();
//        doNothing().when(bookService).add(any());
//        Gson gson = new Gson();
//        String json = gson.toJson(bookDTO);
//        mvc.perform(post("/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk());
//        verify(bookService).add(any());
//    }

//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    public void test_updateBook_Success() throws Exception {
//        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
//        Book book = BookMock.createBook();
//        Gson gson = new Gson();
//        String json = gson.toJson(bookDTO);
//        doNothing().when(bookService).updateBook(anyInt(), any(UpdateBookRequestDTO.class));
//        mvc.perform(put("/books/" + book.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk());
//        verify(bookService).updateBook(anyInt(), any(UpdateBookRequestDTO.class));
//    }
//
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    public void test_updateBook_idNotFound() throws Exception {
//        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
//        Book book = BookMock.createBook();
//        Gson gson = new Gson();
//        String json = gson.toJson(bookDTO);
//        doThrow(BookNotFoundException.class).when(bookService).updateBook(anyInt(), any(UpdateBookRequestDTO.class));
//        mvc.perform(put("/books/" + book.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isNotFound());
//        verify(bookService).updateBook(anyInt(), any(UpdateBookRequestDTO.class));
//    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void test_deleteBook_Success() throws Exception {
        int idMock = 1;
        doNothing().when(bookService).deleteById(idMock);
        mvc.perform(delete("/books/" + idMock)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(bookService).deleteById(idMock);
    }
}
