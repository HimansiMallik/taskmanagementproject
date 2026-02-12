package com.Taskmanagement.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskmanagement.Enum.IssueStatus;
import com.Taskmanagement.Enum.SprintState;
import com.TaskmanagementProject.Entity.Issue;
import com.TaskmanagementProject.Entity.Sprint;
import com.TaskmanagementProject.Repository.IssueRepository;
import com.TaskmanagementProject.Repository.SprintRepository;

import jakarta.transaction.Transactional;

@Service

public class SprintService {
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private IssueRepository issueRepo;
	
	
	
	public Sprint createSprint(Sprint sprint) {
		sprint.setState(SprintState.PLANNED);
		return sprintRepo.save(sprint);
	}
	
	
	@Transactional
	public Issue assignIssueToSprint(Long sprintId,Long issueId) {
		
		Sprint sprint= sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		Issue issue=issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		if(sprint.getState()!= SprintState.COMPLETE) {
			throw new RuntimeException("Can not addissue to completeed sprint");
		}
		issue.setSprintId(sprintId);
		return issueRepo.save(issue);
	}

	@Transactional
	public Sprint startSprint(Long sprintId) {
		
		Sprint sprint=sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		if(sprint.getState()!=SprintState.PLANNED) {
			throw new RuntimeException("Only Planned sprint can be start");
			
		}
		
		if(sprint.getStartDate()==null) {
			sprint.setStartDate(LocalDateTime.now());
		}
		
		return sprintRepo.save(sprint);
	}
	
	@Transactional
	public Sprint closeSrint(Long sprintId) {
		Sprint sprint=sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		sprint.setState(SprintState.COMPLETE);
		if(sprint.getEndDate()==null) {
			
			sprint.setEndDate(LocalDateTime.now());
		}
		List<Issue>issues= issueRepo.findBySprintId(sprintId);
		
		for(Issue issue:issues) {
			
			if(!issue.getStatus().name().equals(IssueStatus.DONE)) {
				issue.setSprintId(null);
				issue.setBackLogPosition(0);
				issueRepo.save(issue);
			}
		}
		
		return sprintRepo.save(sprint);
		
	}
	
	public Map<String,Object>getBurnDownDate(Long sprintId){
		
		Sprint sprint= sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		LocalDateTime start= sprint.getStartDate();
		LocalDateTime end= sprint.getEndDate()!=null?
				        sprint.getEndDate():LocalDateTime.now();
		
	    List<Issue>issue= issueRepo.findBySprintId(sprintId);
	    int totalTask= issue.size();
	
	    Map<String,Object> burnDown = new LinkedHashMap<>();
	    LocalDateTime cursor = start;
	    while(!cursor.isAfter(end)) {
	    	
	    	long completed= issue.stream().filter(i->"DONE".equals(i.getStatus().name())).count();
	    	burnDown.put(cursor.toString(),totalTask-(int)completed);
	    	cursor=cursor.plusDays(1);
	    }
	    
	    Map<String,Object>response= new HashMap<>();
	    response.put("sprintId", sprintId);
	    response.put("startDate", start);
	    response.put("endDate", end);
	    response.put("burnDown", burnDown);
	    
	    return response;
	    
	}
}



