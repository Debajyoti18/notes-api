package com.example.notesapi.controller;

import com.example.notesapi.dto.AuthResponse;
import com.example.notesapi.dto.LoginRequest;
import com.example.notesapi.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(),
							loginRequest.getPassword()
					)
			);

			String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
			AuthResponse authResponse = new AuthResponse(
					token,
					loginRequest.getUsername(),
					"Login successful"
			);
			return ResponseEntity.ok(authResponse);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new AuthResponse(null, null, "Invalid credentials"));
		}
	}
}
