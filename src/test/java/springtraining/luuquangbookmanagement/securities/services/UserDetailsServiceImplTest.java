package springtraining.luuquangbookmanagement.securities.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import springtraining.luuquangbookmanagement.exceptions.NotFoundException;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.repositories.UserRepository;
import springtraining.luuquangbookmanagement.repositories.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @Test
    public void test_loadUserByUsername_Success() {
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        User user = UserMock.createUser();
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        UserDetailsImpl userDetailsImpl = userDetailsService.loadUserByUsername(user.getEmail());
        assertEquals(userDetailsImpl.getUsername(), userDetails.getUsername());
        verify(userRepository).findByEmail(anyString());
    }

    @Test
    public void test_loadUserByUsername_UserNotFound() {
        String email = "email";
                NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(email);
        });
        assertEquals(exception.getMessage(), "User Not Found with email: " + email);
    }
}
