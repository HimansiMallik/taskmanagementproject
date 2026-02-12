package com.TaskmanagementProject.Entity;
import java.time.LocalDateTime;


import com.Taskmanagement.Enum.Role;

import jakarta.persistence.*;
import lombok.*;


@Entity

@Table(name="user_profile")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfile {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	
	@Column(unique=true,nullable=false)
	private String userOfficialEmail;
	
	@Column(nullable=false)
	private String userName;
	
	private String designation;
	private String department;
	private String organizationName;
	
	private LocalDateTime CreatedAt;
	
	@Builder.Default
	private boolean active=true;
	private String password;
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getUserName() {
		return userName;
	}
	public void  setUserName(String userName) {
		this.userName= userName;
	}
	public String getuserOfficialEmail() {
		return userOfficialEmail;
	}
public void setuserOfficialEmail(String userOfficialEmail) {
	this.userOfficialEmail=userOfficialEmail;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password=password;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role=role;
}

	

public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getOrganizationName() {
	return organizationName;
}
public void setOrganizationName(String organizationName) {
	this.organizationName = organizationName;
}
public LocalDateTime getCreatedAt() {
	return CreatedAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
	CreatedAt = createdAt;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}

}
