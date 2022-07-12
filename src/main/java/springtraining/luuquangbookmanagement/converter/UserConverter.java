package springtraining.luuquangbookmanagement.converter;

import org.springframework.stereotype.Service;
import springtraining.luuquangbookmanagement.controllers.auth.dto.LoginRequestDTO;
import springtraining.luuquangbookmanagement.controllers.auth.dto.RegisterRequestDTO;
import springtraining.luuquangbookmanagement.repositories.entities.User;

@Service
public class UserConverter {
    public User convertRegisterRequestDTOToUserEntity(RegisterRequestDTO registerRequestDTO) {
        final User user = new User();
        user.setPassword(registerRequestDTO.getPassword());
        user.setAvatar(registerRequestDTO.getAvatar());
        user.setEmail(registerRequestDTO.getEmail());
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setLastName(registerRequestDTO.getLastName());
        return user;
    }

}
