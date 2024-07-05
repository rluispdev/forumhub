package rluispdev.forum_hub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rluispdev.forum_hub.user.RegisterDTO;
import rluispdev.forum_hub.user.User;
import rluispdev.forum_hub.user.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO data) {
        try {
            // Mapear o DTO para a entidade
            User newUser = new User();
            newUser.setUsername(data.username());
            newUser.setFullName(data.fullName());
            newUser.setEmail(data.email());
            newUser.setPassword(passwordEncoder.encode(data.password())); // Criptografar a senha

            // Salvar o usuário no banco de dados
            userRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }
}
