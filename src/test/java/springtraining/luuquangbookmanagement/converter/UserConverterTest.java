package springtraining.luuquangbookmanagement.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.mocks.auth.AuthMock;
import springtraining.luuquangbookmanagement.repositories.entities.User;

@ExtendWith(SpringExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter converter;

    @Test
    public void test_convertRegisterRequestToUserEntity() {
        final RegisterRequestDTO dto = AuthMock.createRegisterRequestDTO();
        final User user = converter.convertRegisterRequestDTOToUserEntity(dto);
    }

    @Test
    public void test_convertLoginRequestToUserEntity() {
        final RegisterRequestDTO dto = AuthMock.createRegisterRequestDTO();
        final User user = converter.convertRegisterRequestDTOToUserEntity(dto);
    }

}
