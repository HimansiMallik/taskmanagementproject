package com.Taskmanagement.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.TaskmanagementProject.Entity.WorkFlow;
import com.TaskmanagementProject.Entity.WorkFlowTransaction;
import com.TaskmanagementProject.Repository.WorkFlowRepository;
import com.TaskmanagementProject.Repository.WorkFlowTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class WorkFlowService {

	@Autowired
	private WorkFlowRepository workflowRepo;
	
	@Autowired
	private WorkFlowTransactionRepository workflowTransRepo;
	@Transactional
	public WorkFlow createWorkFlow(WorkFlow wf) {
		
		for(WorkFlowTransaction trans:wf.getTransaction())trans.setWorkflow(wf);
		
		return workflowRepo.save(wf);
		
	}
	public List<WorkFlow> listAll(){
		return (List<WorkFlow>) workflowRepo.findAll();
		
		
	}
	
	
	public WorkFlow getById(Long id) {
		return workflowRepo.getById(id);
	}
	
	public Optional<WorkFlow>findByWorkFlowName(String workFlowName){
	
		return workflowRepo.findByWorkFlowName(workFlowName);
	}

	@Transactional
	public WorkFlow updateWorkFlowStatus(Long id,WorkFlow updated) {
		WorkFlow wf=getById(id);
		wf.setWorkFlowName(updated.getWorkFlowName());
		wf.setWorkFlowDescription(updated.getWorkFlowDescription());
		wf.getTransaction().clear();
		
	if(updated.getTransaction() !=null) {
		for(WorkFlowTransaction trans:updated.getTransaction()) {
			trans.setWorkflow(wf);
			wf.getTransaction().add(trans);
			
		}
	}
	 return workflowRepo.save(wf);	
		
	}
	
	public void deleteWorkFlow(Long id) {
		workflowRepo.deleteById(id);
	}
	
	public List<WorkFlowTransaction>allowedTransactions(Long workFlowId, String fromStatus){
		return workflowTransRepo.findByWorkIdFromStatus(workFlowId, fromStatus);
		
	}
	
	public boolean isTransactionAllowed(Long workFlowId, String fromStatus, String toStatus,Set<String>userRole) {
		List<WorkFlowTransaction>list=workflowTransRepo.findByWorkIdFromStatus(workFlowId, fromStatus);
		for(WorkFlowTransaction trans:list) {
			if(trans.getToStatus().equals(toStatus)) {
				String allowed=trans.getAllowedRole();
				if(allowed == null || allowed.isEmpty()) return true;
				
				Set<String> allowedSet = Arrays.stream(allowed.split(",")).map(String::trim).collect(Collectors.toSet());
				for(String r: userRole) if( allowedSet.contains(r)) return true;
				return false;
			}
		}
		
		return false;
		
	}
	
}
