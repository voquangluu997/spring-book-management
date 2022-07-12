package springtraining.luuquangbookmanagement.securities.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsImpl;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class jwtFilterTest {

    @InjectMocks
    JwtFilter jwtFilter;

    @Mock
    UserDetailsServiceImpl userDetailsService;

    @Mock
    TokenManager tokenManager;

    @Test
    public void test_doFilterInternal_Success() {
        String email = "email";
        String token = "token";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        UserDetailsImpl userDetailsImpl = UserMock.createUserDetailsImpl();
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn(email);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetailsImpl);
        when(tokenManager.validateJwtToken(token, userDetailsImpl)).thenReturn(true);
        assertDoesNotThrow(() -> {
            jwtFilter.doFilterInternal(request, response, filterChain);
        });
    }

    @Test
    public void test_doFilterInternal_Success_1() {
        String email = "email";
        String token = "token";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        UserDetailsImpl userDetailsImpl = UserMock.createUserDetailsImpl();
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn(email);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetailsImpl);
        when(tokenManager.validateJwtToken(token, userDetailsImpl)).thenReturn(true);
        assertDoesNotThrow(() -> {
            jwtFilter.doFilterInternal(request, response, filterChain);
        });
    }

}
