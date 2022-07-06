package springtraining.luuquangbookmanagement.controllers.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.controllers.user.dto.UserResponseDTO;
import springtraining.luuquangbookmanagement.securities.service.AuthService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        return authService.register(registerRequest);
    }


}
