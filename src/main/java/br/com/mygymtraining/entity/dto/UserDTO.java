package br.com.mygymtraining.entity.dto;

import br.com.mygymtraining.entity.User;
import br.com.mygymtraining.entity.enums.Role;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "This field is mandatory")
    private String email;
    @NotBlank(message = "This field is mandatory")
    private String username;
    @NotBlank(message = "This field is mandatory")
    private String password;
    @NotBlank(message = "This field is mandatory")
    private Role role;
    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }


}
