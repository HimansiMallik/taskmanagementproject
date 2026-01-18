package com.Taskmanagement.DTO;


import java.time.LocalDateTime;

import com.Taskmanagement.Enum.IssuePriority;
import com.Taskmanagement.Enum.IssueStatus;
import com.Taskmanagement.Enum.IssueType;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueDTO {

	private String issueKey;
	private String issueTitle;
	private String description;
	private IssueType issueType;
	private IssuePriority issuePriority;
	private IssueStatus issueStatus;
	private String assignEmail;
	private String reporterEmail;
	private Long epicId;
	private Long sprintId;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private LocalDateTime dueDate;
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public IssuePriority getIssuePriority() {
		return issuePriority;
	}
	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}
	public IssueStatus getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getAssignEmail() {
		return assignEmail;
	}
	public void setAssignEmail(String assignEmail) {
		this.assignEmail = assignEmail;
	}
	public String getReporterEmail() {
		return reporterEmail;
	}
	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}
	public Long getEpicId() {
		return epicId;
	}
	public void setEpicId(Long epicId) {
		this.epicId = epicId;
	}
	public Long getSprintId() {
		return sprintId;
	}
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDtae) {
		this.dueDate = dueDtae;
	}
	
	
	
	
}
