package com.Taskmanagement.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Taskmanagement.Service.WorkFlowService;
import com.TaskmanagementProject.Entity.WorkFlow;
import com.TaskmanagementProject.Entity.WorkFlowTransaction;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@PostMapping("/create")
	public ResponseEntity<WorkFlow>create(@RequestBody WorkFlow wf){
 return ResponseEntity.ok(workFlowService.createWorkFlow(wf));
	}
	@GetMapping("/list")
	public ResponseEntity<List<WorkFlow>>allList(){
		return ResponseEntity.ok(workFlowService.listAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<WorkFlow>get(@PathVariable Long id){
		return ResponseEntity.ok(workFlowService.getById(id));
	}
	
	@GetMapping("/{workFlowName}")
	public ResponseEntity<Optional<WorkFlow>>getByName(@PathVariable String workFlowName){
		return ResponseEntity.ok(workFlowService.findByWorkFlowName(workFlowName));
	}
	
	
	@PutMapping("/updated/{id}")
	public ResponseEntity<WorkFlow>update(@PathVariable Long id, @RequestBody WorkFlow wf){
 return ResponseEntity.ok(workFlowService.updateWorkFlowStatus(id, wf));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable Long id){
		workFlowService.deleteWorkFlow(id);
		return ResponseEntity.ok("Deleted");
		
		
	}
	
	@GetMapping("/{id}/transaction/{from}")
	public ResponseEntity<List<WorkFlowTransaction>> allowed(@PathVariable Long id, @PathVariable String fromStatus){
		return ResponseEntity.ok(workFlowService.allowedTransactions(id, fromStatus));
	}
}
