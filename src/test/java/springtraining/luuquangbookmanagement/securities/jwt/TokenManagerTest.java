package springtraining.luuquangbookmanagement.securities.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import springtraining.luuquangbookmanagement.mocks.user.UserMock;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)

public class TokenManagerTest {

    @InjectMocks
    private TokenManager tokenManager;

    @Test
    public void test_generateToken_Success() {
        ReflectionTestUtils.setField(tokenManager, "jwtSecret", "uQuangmskdmksmkdaosidjoisamdkamskodm12iu1e8u298ismkdmkmdmedlkamldkmalkwmdlawmlkdmalkwdedjnejn");
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        String token = tokenManager.generateToken(userDetails);
        assertEquals(tokenManager.generateToken(userDetails), token);
    }

    @Test
    public void test_validateJwtToken_Success() {
        ReflectionTestUtils.setField(tokenManager, "jwtSecret", "uQuangmskdmksmkdaosidjoisamdkamskodm12iu1e8u298ismkdmkmdmedlkamldkmalkwmdlawmlkdmalkwdedjnejn");
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        String token = tokenManager.generateToken(userDetails);
        assertEquals(tokenManager.validateJwtToken(token, userDetails), true);
    }

    @Test
    public void test_getUsernameFromToken_Success() {
        ReflectionTestUtils.setField(tokenManager, "jwtSecret", "uQuangmskdmksmkdaosidjoisamdkamskodm12iu1e8u298ismkdmkmdmedlkamldkmalkwmdlawmlkdmalkwdedjnejn");
        UserDetailsImpl userDetails = UserMock.createUserDetailsImpl();
        String token = tokenManager.generateToken(userDetails);
        assertEquals(tokenManager.getUsernameFromToken(token), userDetails.getUsername());
    }

}
