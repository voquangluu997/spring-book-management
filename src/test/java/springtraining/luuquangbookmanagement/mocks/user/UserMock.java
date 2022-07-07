package springtraining.luuquangbookmanagement.mocks.user;

import springtraining.luuquangbookmanagement.repositories.entities.User;
import springtraining.luuquangbookmanagement.securities.service.UserDetailsImpl;

import java.util.UUID;

public class UserMock {

    public static User createUser() {
        return User.builder()
                .email("email")
                .password("password")
                .firstName("firstName")
                .lastName("firstName")
                .build();
    }

    public static UserDetailsImpl createUserDetailsImpl() {
        return UserDetailsImpl.builder()
                .username("email")
                .password("password")
                .firstName("firstName")
                .lastName("firstName")
                .build();
    }

}
