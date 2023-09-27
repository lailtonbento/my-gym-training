package br.com.mygymtraining.controller;

import br.com.mygymtraining.entity.dto.UserDTO;
import br.com.mygymtraining.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllPageable(
            @PageableDefault(page = 0, sort = {"username"}) Pageable pageable) {
        return ResponseEntity.ok(userService.listPageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO){
        return userService.update(id, userDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
    }
}
