package com.Taskmanagement.DTO;



import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserProfileDTO {
 public String userName;
 public String userOfficialEmail;
 public String department;
 public String designation;
 public String orgsnizationName;
 public boolean active;
 
 public LocalDateTime CreatedAt;

 public String getUserName() {
	return userName;
 }

 public void setUserName(String userName) {
	this.userName = userName;
 }

 public String getUserOfficialEmail() {
	return userOfficialEmail;
 }

 public void setUserOfficialEmail(String userOfficialEmail) {
	this.userOfficialEmail = userOfficialEmail;
 }

 public String getDepartment() {
	return department;
 }

 public void setDepartment(String department) {
	this.department = department;
 }

 public String getDesignation() {
	return designation;
 }

 public void setDesignation(String designation) {
	this.designation = designation;
 }

 public String getOrgsnizationName() {
	return orgsnizationName;
 }

 public void setOrgsnizationName(String orgsnizationName) {
	this.orgsnizationName = orgsnizationName;
 }

 public boolean isActive() {
	return active;
 }

 public void setActive(boolean active) {
	this.active = active;
 }

 public LocalDateTime getCreatedAt() {
	return CreatedAt;
 }

 public void setCreatedAt(LocalDateTime createdAt) {
	CreatedAt = createdAt;
 }

 
 
 
 
}
