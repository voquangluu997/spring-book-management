package springtraining.luuquangbookmanagement.services.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.controllers.user.dto.UserResponseDTO;
import springtraining.luuquangbookmanagement.converter.UserConverter;
import springtraining.luuquangbookmanagement.exceptions.BadRequestException;
import springtraining.luuquangbookmanagement.exceptions.NotFoundException;
import springtraining.luuquangbookmanagement.mocks.auth.AuthMock;
import springtraining.luuquangbookmanagement.mocks.role.RoleMock;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.repositories.RoleRepository;
import springtraining.luuquangbookmanagement.repositories.UserRepository;
import springtraining.luuquangbookmanagement.repositories.entities.User;
import springtraining.luuquangbookmanagement.securities.jwt.TokenManager;
import springtraining.luuquangbookmanagement.securities.service.AuthService;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsImpl;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class AuthServiceTest {
    @InjectMocks
    AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserConverter converter;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void test_login_Success() {
        UserResponseDTO userResponseDTO = UserMock.createUserRestponseDTO();
        LoginRequestDTO loginRequestDTO = AuthMock.createLoginRequestDTO();
        User user = UserMock.createUser();
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(tokenManager.generateToken(any())).thenReturn("token");
        assertEquals(authService.login(loginRequestDTO).getEmail(), userResponseDTO.getEmail());
        assertEquals(authService.login(loginRequestDTO).getRole(), userResponseDTO.getRole());
        verify(userRepository, times(2)).findByEmail(anyString());
        verify(authenticationManager, times(2)).authenticate(any(Authentication.class));
        verify(userDetailsService, times(2)).loadUserByUsername(anyString());
        verify(tokenManager, times(2)).generateToken(any());
    }

    @Test
    public void test_login_UserNotFound() {

        LoginRequestDTO loginRequestDTO = AuthMock.createLoginRequestDTO();
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authService.login(loginRequestDTO);
        });
        assertEquals(exception.getMessage(), "User with email " + loginRequestDTO.getEmail() + " not found ");
    }

    @Test
    public void test_register_Success() {
        UserResponseDTO userResponseDTO = UserMock.createUserRestponseDTO();
        RegisterRequestDTO registerRequestDTO = AuthMock.createRegisterRequestDTO();
        User user = UserMock.createUser();
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        String token = "token";
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(converter.convertRegisterRequestDTOToUserEntity(any(RegisterRequestDTO.class))).thenReturn(user);
        when(roleRepository.findByName(anyString())).thenReturn(RoleMock.createRole());
        when(userRepository.save(user)).thenReturn(user);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(tokenManager.generateToken(any(UserDetailsImpl.class))).thenReturn("token");
        assertEquals(authService.register(registerRequestDTO).getEmail(), userResponseDTO.getEmail());
        verify(userRepository).findByEmail(anyString());
        verify(roleRepository).findByName(anyString());
        verify(userDetailsService).loadUserByUsername(anyString());
        verify(tokenManager).generateToken(any(UserDetailsImpl.class));
        verify(converter).convertRegisterRequestDTOToUserEntity(any(RegisterRequestDTO.class));
    }

    @Test
    public void test_Register_EmailAlreadyBeingUsed() {
        RegisterRequestDTO registerRequestDTO = AuthMock.createRegisterRequestDTO();
        User user = UserMock.createUser();
        when(userRepository.findByEmail(registerRequestDTO.getEmail())).thenReturn(user);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            authService.register(registerRequestDTO);
        });
        assertEquals(exception.getMessage(), "This email address is already being used");
        verify(userRepository).findByEmail(registerRequestDTO.getEmail());
    }
}
