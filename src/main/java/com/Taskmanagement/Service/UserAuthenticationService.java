package com.Taskmanagement.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Taskmanagement.DTO.AuthResponseDTO;
import com.Taskmanagement.DTO.LoginRequestDTO;
import com.Taskmanagement.DTO.RegisterRequestDTO;
import com.TaskmanagementProject.Entity.UserAuthentication;
import com.TaskmanagementProject.Repository.UserAuthenticationRepository;
import com.TaskmanagementProject.Security.JWTTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {
	@Autowired
	private UserAuthenticationRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
 AuthenticationManager authenticationManager;
	 @Autowired
	 private JWTTokenUtil jwtUtil;

	 private PasswordEncoder passwordEncode;
	 
	 public void register(RegisterRequestDTO register) {
		 
		
		 
		 if(userRepo.findByUserOfficialEmail(register.userOfficialEmail).isPresent()) {
			 throw new RuntimeException("Profile already exists"+register.userOfficialEmail);
		 }
		 
		 
		 UserAuthentication user= new UserAuthentication();
		 user.setUserName(register.userName);
		 user.setUserOfficialEmail(register.userOfficialEmail);
		 user.setPassword(passwordEncoder.encode(register.password));
		 user.setRole(register.role);
		 
		 
		 userRepo.save(user);
		 
	 }
	 
	 public AuthResponseDTO login(LoginRequestDTO dto) 
	 {
		 
		 UserAuthentication user= userRepo.findByUserOfficialEmail(dto.userOfficialEmail)
				 .orElseThrow(()-> new RuntimeException("User not found"));
		 
		 if(!passwordEncode.matches(dto.password,user.getPassword())) {
			 throw new RuntimeException("Invalid Credential");
		 }
	
		
		 
		 String token=jwtUtil.generateToken(user);
		 
		 return new AuthResponseDTO(token,"Token got generate");
	 }

}
