package springtraining.luuquangbookmanagement.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.providers.UserProvider;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsImpl;

import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class UserProviderTest {

    @InjectMocks
    UserProvider userProvider;

    @Test
    public void test_getCurrentUser_Success() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl  userDetails = userProvider.getCurrentUser();
    }
}
