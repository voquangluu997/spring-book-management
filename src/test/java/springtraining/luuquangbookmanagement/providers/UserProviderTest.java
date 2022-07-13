package springtraining.luuquangbookmanagement.providers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserProviderTest {

    @InjectMocks
    UserProvider userProvider;

    @Test
    @WithMockUser(username = "user")
    public void test_getCurrentUser_Success() {
        Authentication authentication = mock(Authentication.class);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        when(userProvider.getCurrentUser()).thenReturn(userDetails);
        assertEquals(userProvider.getCurrentUser().getUsername(), userDetails.getUsername());
    }
}
