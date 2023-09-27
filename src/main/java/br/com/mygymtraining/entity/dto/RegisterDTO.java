package br.com.mygymtraining.entity.dto;

import br.com.mygymtraining.entity.enums.Role;

public record RegisterDTO (String username, String email, String password, Role role){
}
