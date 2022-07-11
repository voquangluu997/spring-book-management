package springtraining.luuquangbookmanagement.controllers.auth;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.controllers.user.dto.UserResponseDTO;
import springtraining.luuquangbookmanagement.mocks.auth.AuthMock;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.securities.service.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthService authService;

    @Test
    public void test_login_Success() throws Exception {
        UserResponseDTO userResponseDTO = UserMock.createUserRestponseDTO();
        LoginRequestDTO loginRequestDTO = AuthMock.createLoginRequestDTO();
        when(authService.login(any(LoginRequestDTO.class))).thenReturn(userResponseDTO);
        Gson gson = new Gson();
        String json = gson.toJson(loginRequestDTO);
        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", Matchers.equalTo(userResponseDTO.getEmail())));

    }

    @Test
    public void test_register_Success() throws Exception {
        UserResponseDTO userResponseDTO = UserMock.createUserRestponseDTO();
        RegisterRequestDTO registerRequestDTO = AuthMock.createRegisterRequestDTO();
        when(authService.register(any(RegisterRequestDTO.class))).thenReturn(userResponseDTO);
        Gson gson = new Gson();
        String json = gson.toJson(registerRequestDTO);
        mvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(json)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", Matchers.equalTo(userResponseDTO.getToken())));
    }

}
