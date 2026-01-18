package com.Taskmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanagement.DTO.AuthResponseDTO;
import com.Taskmanagement.DTO.LoginRequestDTO;
import com.Taskmanagement.DTO.RegisterRequestDTO;
import com.Taskmanagement.Service.UserAuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestBody RegisterRequestDTO dto){
		
				authService.register(dto);
				return ResponseEntity.ok("User register Successful");
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO>login(@RequestBody LoginRequestDTO dto){
		return ResponseEntity.ok(authService.login(dto));
	}

}

