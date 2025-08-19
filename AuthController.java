package com.careerconnect.talent.controller;

import com.careerconnect.talent.model.Account;
import com.careerconnect.talent.repo.AccountRepo;
import com.careerconnect.talent.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountRepo accountRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(AccountRepo accountRepo, JwtUtil jwtUtil){ this.accountRepo=accountRepo; this.jwtUtil=jwtUtil; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        String username = body.get("username");
        String password = body.get("password");
        String role = body.get("role");
        if(accountRepo.existsByUsername(username)) return ResponseEntity.badRequest().body(Map.of("error","Username exists"));
        String hashed = encoder.encode(password);
        Account a = new Account(username, hashed, role==null?"ROLE_SEEKER":role);
        accountRepo.save(a);
        return ResponseEntity.ok(Map.of("message","registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        String username = body.get("username");
        String password = body.get("password");
        Account a = accountRepo.findByUsername(username);
        if(a==null || !encoder.matches(password, a.getPassword())) return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
        String token = jwtUtil.generateToken(a.getUsername(), a.getRole());
        return ResponseEntity.ok(Map.of("token", token, "username", a.getUsername(), "role", a.getRole()));
    }
}
