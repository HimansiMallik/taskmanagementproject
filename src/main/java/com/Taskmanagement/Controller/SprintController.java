package com.Taskmanagement.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanagement.Service.SprintService;
import com.TaskmanagementProject.Entity.Issue;
import com.TaskmanagementProject.Entity.Sprint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/sprints")
@RequiredArgsConstructor

public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Sprint>createSprint(@RequestBody Sprint sprint){
		return ResponseEntity.ok(sprintService.createSprint(sprint));
	}
	
	@PutMapping("/assign/{sprintId}/{issueId}")
	public ResponseEntity<Issue> assignedIssueToSprint(@PathVariable Long sprintId,@PathVariable Long issueId){
		return ResponseEntity.ok(sprintService.assignIssueToSprint(sprintId, issueId));
	}
	
	@PutMapping("/{sprintId}/start")
	public ResponseEntity<Sprint>startSprint(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.startSprint(sprintId));
	}
	@PutMapping("/{sprintId}/close")
	public ResponseEntity<Sprint>closeSprint(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.closeSrint(sprintId));
	}
	
	@GetMapping("/{sprintId}/burnDown")
	public ResponseEntity<Map<String,Object>>getBurnDown(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.getBurnDownDate(sprintId));
	}

}
