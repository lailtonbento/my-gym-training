package br.com.mygymtraining.service;

import br.com.mygymtraining.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> listUsers();
    Page<UserDTO> listPageable(Pageable pageable);

    void deleteById (Long id);
    UserDTO save (UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
}
