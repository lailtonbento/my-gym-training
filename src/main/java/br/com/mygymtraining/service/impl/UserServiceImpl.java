package br.com.mygymtraining.service.impl;

import br.com.mygymtraining.entity.User;
import br.com.mygymtraining.entity.dto.UserDTO;
import br.com.mygymtraining.repository.UserRepository;
import br.com.mygymtraining.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream()
                .map(User::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> listPageable(Pageable pageable) {
        return userRepository.findAll(pageable).map(User::toDTO);
    }


    @Override
    public void deleteById(Long id) {
        existById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userRepository.save(userDTO.toEntity())
                .toDTO();
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        existById(id);
        userDTO.setId(id);
        return userRepository.save(userDTO.toEntity())
                .toDTO();
    }

    @SneakyThrows
    public void existById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new Exception("Exercise not found");
        }
    }

    @SneakyThrows
    public UserDTO findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Exercise Not Found")).toDTO();
    }
}
