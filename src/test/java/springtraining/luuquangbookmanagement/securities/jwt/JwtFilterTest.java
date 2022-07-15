package springtraining.luuquangbookmanagement.securities.jwt;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsImpl;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class JwtFilterTest {

    @InjectMocks
    JwtFilter jwtFilter;

    @Mock
    UserDetailsServiceImpl userDetailsService;

    @Mock
    TokenManager tokenManager;

    @Test
    public void test_doFilterInternal_Success_WithHeaderNull() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        assertDoesNotThrow(() -> jwtFilter.doFilterInternal(request, response, filterChain));
    }

    @Test
    public void test_doFilterInternal_Success_WithBearerHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer xxx");
        FilterChain filterChain = mock(FilterChain.class);
        UserDetailsImpl userDetailsImpl = UserMock.createUserDetailsImpl();
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn("ff");
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetailsImpl);
        when(tokenManager.validateJwtToken(anyString(), any(UserDetailsImpl.class))).thenReturn(true);
        assertDoesNotThrow(() -> jwtFilter.doFilterInternal(request, response, filterChain));
        verify(tokenManager).getUsernameFromToken(anyString());
        verify(userDetailsService).loadUserByUsername(anyString());
        verify(tokenManager).validateJwtToken(anyString(), any(UserDetailsImpl.class));
    }

    @Test
    public void test_doFilterInternal_IllegalArgumentException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer xxx");
        FilterChain filterChain = mock(FilterChain.class);
        when(tokenManager.getUsernameFromToken(anyString())).thenThrow(IllegalArgumentException.class);
        assertDoesNotThrow(() -> jwtFilter.doFilterInternal(request, response, filterChain));
        verify(tokenManager).getUsernameFromToken(anyString());
    }

    @Test
    public void test_doFilterInternal_ExpiredJwtException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer xxx");
        FilterChain filterChain = mock(FilterChain.class);
        when(tokenManager.getUsernameFromToken(anyString())).thenThrow(ExpiredJwtException.class);
        assertDoesNotThrow(() -> jwtFilter.doFilterInternal(request, response, filterChain));
        verify(tokenManager).getUsernameFromToken(anyString());
    }
}
