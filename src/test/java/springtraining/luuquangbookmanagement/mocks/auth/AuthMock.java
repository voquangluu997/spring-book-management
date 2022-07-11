package springtraining.luuquangbookmanagement.mocks.auth;

import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;

public class AuthMock {
    public static LoginRequestDTO createLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email("email")
                .password("password")
                .build();
    }

    public static RegisterRequestDTO createRegisterRequestDTO() {
        return RegisterRequestDTO.builder()
                .email("email")
                .password("password")
                .firstName("fn")
                .lastName("ln")
                .avatar("avt")
                .build();
    }

}
