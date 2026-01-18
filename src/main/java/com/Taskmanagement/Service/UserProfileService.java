package com.Taskmanagement.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskmanagement.DTO.UserProfileDTO;
import com.Taskmanagement.Enum.Role;
import com.TaskmanagementProject.Entity.UserAuthentication;
import com.TaskmanagementProject.Entity.UserProfile;
import com.TaskmanagementProject.Repository.UserAuthenticationRepository;
import com.TaskmanagementProject.Repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepo = null;
    
    @Autowired
    private UserAuthenticationRepository userRepo;

    public UserProfileDTO updateUserProfile(UserProfileDTO dto) {

        UserProfile user = userProfileRepo.findByUserOfficialEmail(dto.getUserOfficialEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // UPDATE the existing entity instead of creating a new one
        user.setUserName(dto.getUserName());
        user.setuserOfficialEmail(dto.getUserOfficialEmail());
        user.setDepartment(dto.getDepartment());
        user.setDesignation(dto.getDesignation());
        user.setOrganizationName(dto.getOrgsnizationName());
        user.setActive(dto.isActive());
        user.setCreatedAt(LocalDateTime.now()); 

        userProfileRepo.save(user);

        return toDTO(user);
    }
    
    public UserAuthentication uploadRole(Long id,Role role) {
    	UserAuthentication user= userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    	
    	user.setRole(role);
    	return userRepo.save(user);
    }

    public List<UserProfileDTO> getAllUserProfile() {
        return userProfileRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserProfileDTO getProfileByEmail(String userOfficialEmail) {
        UserProfile user = userProfileRepo.findByUserOfficialEmail(userOfficialEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toDTO(user);
    }

    public List<UserProfileDTO> getUserByDepartment(String department) {
        return userProfileRepo.findUserByDepartment(department).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserProfileDTO> getUserByDesignation(String designation) {
        return userProfileRepo.findUserByDesignation(designation).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private UserProfileDTO toDTO(UserProfile user) {
        UserProfileDTO dto = new UserProfileDTO();

        dto.setUserName(user.getUserName());
        dto.setUserOfficialEmail(user.getuserOfficialEmail());
        dto.setDesignation(user.getDesignation());
        dto.setDepartment(user.getDepartment());
        dto.setOrgsnizationName(user.getOrganizationName());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt()); 

        return dto;
    }
}