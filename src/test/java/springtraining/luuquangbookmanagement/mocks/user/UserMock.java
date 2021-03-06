package springtraining.luuquangbookmanagement.mocks.user;

import springtraining.luuquangbookmanagement.controllers.user.dto.UserResponseDTO;
import springtraining.luuquangbookmanagement.mocks.role.RoleMock;
import springtraining.luuquangbookmanagement.repositories.entities.Role;
import springtraining.luuquangbookmanagement.repositories.entities.User;
import springtraining.luuquangbookmanagement.securities.services.UserDetailsImpl;

public class UserMock {

    public static User createUser() {

        Role role = RoleMock.createRole();
        return User.builder().email("email@gmail.com").password("password").firstName("firstName").lastName("firstName").avatar("avatar").role(role).build();
    }

    public static UserResponseDTO createUserRestponseDTO() {
        return UserResponseDTO.builder().email("email@gmail.com").avatar("avatar").firstName("firstName").lastName("lastName").token("token").role("USER").build();
    }

    public static UserDetailsImpl createUserDetailsImpl() {
        return UserDetailsImpl.builder().username("email@gmail.com").password("password").firstName("firstName").lastName("firstName").build();
    }

}
