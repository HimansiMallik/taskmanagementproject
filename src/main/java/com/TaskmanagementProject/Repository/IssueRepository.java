package com.TaskmanagementProject.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Taskmanagement.Enum.IssueStatus;
import com.TaskmanagementProject.Entity.Issue;


@Repository
public interface IssueRepository extends JpaRepository<Issue,Long>{
	
	Optional<Issue>findByIssueKey(String issueKey);
	Optional<Issue>findById(Long id);
	List<Issue>findByAssigneeEmail(String assigneeEmail);
	List<Issue>findBySprintId(Long sprintId);
	List<Issue>findByIssueStatus(IssueStatus issueStatus);
    
}