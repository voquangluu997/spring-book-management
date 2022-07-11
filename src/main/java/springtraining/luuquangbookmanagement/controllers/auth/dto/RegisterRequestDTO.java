package springtraining.luuquangbookmanagement.controllers.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
@Builder
public class RegisterRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private String avatar;

}
