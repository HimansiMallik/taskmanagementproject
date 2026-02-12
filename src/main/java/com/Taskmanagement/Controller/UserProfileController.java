package com.Taskmanagement.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanagement.DTO.UserProfileDTO;
import com.Taskmanagement.Service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userProfile")
@RequiredArgsConstructor
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	// URL: PUT http://localhost:8080/api/userProfile/test@gmail.com
	@PutMapping("/{email}")
	public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable String email, @RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(userProfileService.updateUserProfile(dto));
	}
	
	// URL: GET http://localhost:8080/api/userProfile/email/test@gmail.com
	@GetMapping("/email/{email:.+}")
	public ResponseEntity<UserProfileDTO> getUserByEmail(@PathVariable("email") String userOfficialEmail){
		return ResponseEntity.ok(userProfileService.getProfileByEmail(userOfficialEmail));
	}
	
	// URL: GET http://localhost:8080/api/userProfile/designation/Manager
	@GetMapping("/designation/{designation}")
	public ResponseEntity<List<UserProfileDTO>> getUserByDesignation(@PathVariable String designation){
		return ResponseEntity.ok(userProfileService.getUserByDesignation(designation));
	}
	
	// URL: GET http://localhost:8080/api/userProfile/department/IT
	@GetMapping("/department/{department}")
	public ResponseEntity<List<UserProfileDTO>> getUserByDepartment(@PathVariable String department){
		return ResponseEntity.ok(userProfileService.getUserByDepartment(department));
	}
}