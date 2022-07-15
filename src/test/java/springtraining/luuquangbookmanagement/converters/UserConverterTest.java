package springtraining.luuquangbookmanagement.converters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.mocks.auth.AuthMock;

@ExtendWith(SpringExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter converter;

    @Test
    public void test_convertRegisterRequestToUserEntity() {
        final RegisterRequestDTO dto = AuthMock.createRegisterRequestDTO();
        converter.convertRegisterRequestDTOToUserEntity(dto);
    }

    @Test
    public void test_convertLoginRequestToUserEntity() {
        final RegisterRequestDTO dto = AuthMock.createRegisterRequestDTO();
        converter.convertRegisterRequestDTOToUserEntity(dto);
    }

}
