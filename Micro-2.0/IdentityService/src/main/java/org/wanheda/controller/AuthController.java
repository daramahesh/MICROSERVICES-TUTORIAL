package org.wanheda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.wanheda.dto.AuthRequest;
import org.wanheda.entity.UserInfo;
import org.wanheda.serviceimpl.AuthServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()) {

            return this.authService.generateToken(authRequest.getUsername());
        }else {
            throw new RuntimeException("user not found");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        this.authService.validateToken(token);
        return "token is valid";
    }
}
