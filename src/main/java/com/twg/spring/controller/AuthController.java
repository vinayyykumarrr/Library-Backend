package com.twg.spring.controller;
import com.twg.spring.config.JwtService;
import com.twg.spring.dto.AuthRequest;
import com.twg.spring.dto.AuthResponse;
import com.twg.spring.entity.Users;
import com.twg.spring.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UsersRepository usersRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private JwtService jwtService;
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("USER");
        usersRepository.save(user);
        String str="Register successfully your id is: "+user.getId();
//        String token = jwtService.generateToken(user.getEmail());
        return str;
    }
    @PostMapping("/admin/register")
    public String adminregister(@RequestBody AuthRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole(request.getRole());
        usersRepository.save(user);
        String str="Register successfully your id is: "+user.getId();
//        String token = jwtService.generateToken(user.getEmail());
        return str;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtService.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
