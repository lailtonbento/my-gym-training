package br.com.mygymtraining.controller;

import br.com.mygymtraining.controller.config.security.JwtService;
import br.com.mygymtraining.entity.User;
import br.com.mygymtraining.entity.dto.AuthenticationDTO;
import br.com.mygymtraining.entity.dto.LoginResponseDTO;
import br.com.mygymtraining.entity.dto.RegisterDTO;
import br.com.mygymtraining.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RabbitMessagingTemplate rabbitTemplate;

    private static final String AUTH_REGISTER_ROUTINGKEY = "auth.v1.user-register";

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = jwtService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        Message message = new Message(newUser.getId().toString().getBytes());
        rabbitTemplate.convertAndSend(AUTH_REGISTER_ROUTINGKEY, message);

        return ResponseEntity.ok().build();
    }
}
