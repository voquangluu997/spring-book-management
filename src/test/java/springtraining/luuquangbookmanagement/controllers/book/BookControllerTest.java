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
import springtraining.luuquangbookmanagement.controllers.book.dto.AddBookRequestDTO;
import springtraining.luuquangbookmanagement.controllers.book.dto.UpdateBookRequestDTO;
import springtraining.luuquangbookmanagement.exceptions.BookNotFoundException;
import springtraining.luuquangbookmanagement.mocks.book.BookMock;
import springtraining.luuquangbookmanagement.repositories.entities.Book;
import springtraining.luuquangbookmanagement.services.BookService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @WithMockUser(roles = {"USER"})
    public void test_getBookById_Found() throws Exception {
        final Book book = BookMock.createBook();
        when(bookService.getById(book.getId())).thenReturn(book);
        mvc.perform(MockMvcRequestBuilders.get("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void test_getBookById_NotFound() throws Exception {
        final Book book = BookMock.createBook();
        when(bookService.getById(book.getId())).thenThrow(BookNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders.get("/books/" + (book.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void test_addBook_Success() throws Exception {
        AddBookRequestDTO bookDTO = BookMock.createAddBookRequestDTO();
        doNothing().when(bookService).addBook(bookDTO);
        Gson gson = new Gson();
        String json = gson.toJson(bookDTO);
        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void test_updateBook_Success() throws Exception {
        UpdateBookRequestDTO bookDTO = BookMock.createUpdateBookRequestDTO();
        Book book = BookMock.createBook();
        Gson gson = new Gson();
        String json = gson.toJson(bookDTO);
        doNothing().when(bookService).updateBook(book.getId(), bookDTO);
        mvc.perform(put("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
