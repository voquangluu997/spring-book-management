package springtraining.luuquangbookmanagement.controllers.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String token;
    private String role;
}