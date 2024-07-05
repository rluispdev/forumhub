package rluispdev.forum_hub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rluispdev.forum_hub.infra.security.TokenJWTDTO;
import rluispdev.forum_hub.infra.security.TokenService;
import rluispdev.forum_hub.user.LoginAuthenticationDTO;
import rluispdev.forum_hub.user.User;

@RestController
@RequestMapping("/login")
public class AthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> toMakeLogin(@RequestBody @Valid LoginAuthenticationDTO data) {
        System.out.println(data);
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
        }
    }
}
