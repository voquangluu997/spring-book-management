package springtraining.luuquangbookmanagement.mocks.role;

import springtraining.luuquangbookmanagement.repositories.entities.Role;

public class RoleMock {
    public static Role createRole() {
        return Role.builder()
                .name("USER")
                .build();
    }
}
