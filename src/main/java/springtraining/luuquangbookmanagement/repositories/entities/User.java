package springtraining.luuquangbookmanagement.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    private String avatar;

    @NotNull
    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("users")
    private Role role;

}
