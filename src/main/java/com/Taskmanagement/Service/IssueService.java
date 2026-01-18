package com.Taskmanagement.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Taskmanagement.DTO.IssueDTO;
import com.Taskmanagement.Enum.IssuePriority;
import com.Taskmanagement.Enum.IssueStatus;
import com.TaskmanagementProject.Entity.Issue;
import com.TaskmanagementProject.Entity.IssueComent;
import com.TaskmanagementProject.Repository.EpicRepository;
import com.TaskmanagementProject.Repository.IssueCommentRepository;
import com.TaskmanagementProject.Repository.IssueRepository;
import com.TaskmanagementProject.Repository.SprintRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {
	
	@Autowired
	private IssueRepository issueRepo;
	
	@Autowired
	private IssueCommentRepository issuecommentRepo;
	
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private EpicRepository epicRepo;
	
	
	
	

    public String generatedKey(Long issueId) {
        return "ISSUE-" + issueId;
    }
	
	
	@Transactional
	public IssueDTO createIssue(IssueDTO dto) {
		
		Issue issue = new Issue();
		
		issue.setIssueTitle(dto.getIssueTitle());
		issue.setIssueType(dto.getIssueType());
		issue.setIssueKey("PROJECT"+issue.getId());
		issue.setAssigneeEmail(dto.getAssignEmail());
		issue.setRepoterEmail(dto.getReporterEmail());
		issue.setCreatedAt(dto.getCreatedAt());
		issue.setDesription(dto.getDescription());
		issue.setPriority(IssuePriority.LOW);
		issue.setStatus(IssueStatus.OPEN);
		issue.setUpdateAt(dto.getUpdateAt());
		issue.setDueDate(dto.getDueDate());
		
		
		if(dto.getEpicId() !=null) {
			epicRepo.findById(dto.getEpicId()).orElseThrow(()-> new RuntimeException("Epic not found"));
			issue.setEpicId(dto.getEpicId());
			
		}
		
		if(dto.getSprintId() !=null) {
			sprintRepo.findById(dto.getSprintId()).orElseThrow(()-> new RuntimeException("Sprint not found"));
			
			issue.setSprintId(dto.getSprintId());
		}
		
		issueRepo.save(issue);
		
		return toDTO(issue);
	}

	
	public IssueDTO getById(Long id) {
		
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not found"));
		return toDTO(issue);
	}
	
	
	@Transactional
	public IssueDTO updateIssueStatus(Long id,IssueStatus status,String performBy) {
		
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		IssueStatus newStatus;
		
		try {
			newStatus = IssueStatus.valueOf(String.valueOf(status).toUpperCase().trim());
		} catch (Exception e) {
			throw new RuntimeException("Invalid Status"+status);
		}
		
		issue.setStatus(newStatus);
		issueRepo.save(issue);
		
		IssueComent comment = new IssueComent();
		comment.setIssueId(id);
		comment.setAuthorEmail(performBy);
		comment.setBody("Status changed to :"+status);
		
		return toDTO(issue);
		
	}
	
	@Transactional
	public IssueComent addComment(Long issueId,String authorEmail,String body) {
		
		issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		IssueComent comment = new IssueComent();
		comment.setIssueId(issueId);
		comment.setAuthorEmail(authorEmail);
		comment.setBody(body);
		
		return issuecommentRepo.save(comment);
		
	}
	
	
	
	
	public List<IssueDTO>search(Map<String,String>filter){
		
		if(filter.containsKey("assignee")) {
			
			return issueRepo.findByAssigneeEmail(filter.get("assignee")).stream().map(this::toDTO).collect(Collectors.toList());
			
		}
		
		
		if(filter.containsKey("status")) {
			String statusStr= filter.get("status");
			IssueStatus  status;
			
			try {
				status= IssueStatus.valueOf(statusStr.toUpperCase().trim());
				
			} catch (Exception e) {
				throw new RuntimeException("invalid Status:"+statusStr+"| Allowed"+Arrays.toString(IssueStatus.values()));
			}
		
			return issueRepo.findByIssueStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
		
	 }
		
		if(filter.containsKey("sprint")) {
			Long sprintId = Long.valueOf(filter.get("sprint"));
			return issueRepo.findBySprintId(sprintId).stream().map(this::toDTO).collect(Collectors.toList());
		}
		
		return issueRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}


	private IssueDTO toDTO(Issue issue) {
		
		IssueDTO dto = new IssueDTO();
		
		dto.setIssueTitle(issue.getIssueTitle());
		dto.setDescription(issue.getDesription());
		dto.setCreatedAt(issue.getCreatedAt());
		dto.setIssueKey(issue.getIssueKey());
		dto.setIssuePriority(issue.getPriority());
		dto.setIssueStatus(issue.getStatus());
		dto.setIssueType(issue.getIssueType());
		dto.setAssignEmail(issue.getAssigneeEmail());
		dto.setReporterEmail(issue.getRepoterEmail());
		dto.setUpdateAt(issue.getUpdateAt());
		dto.setEpicId(issue.getEpicId());
		dto.setSprintId(issue.getSprintId());
		
		
		return dto;
	}
}

