package com.TaskmanagementProject.Entity;



import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
@Entity
@Table(
    name = "workflow_transaction",
    indexes = {
        @Index(
            name = "idx_wf_from_to",
            columnList = "workflow_id, from_status, to_status"
        )
    }
)
public class WorkFlowTransaction {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="workflow_id",nullable=false)
	private WorkFlow Workflow;
	
	private String fromStatus;
	private String toStatus;
	
	private String transactionName;
	private String allowedRole;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public WorkFlow getWorkflow() {
		return Workflow;
	}
	public void setWorkflow(WorkFlow Workflow) {
		this.Workflow = Workflow;
	}
	public String getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}
	public String getToStatus() {
		return toStatus;
	}
	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getAllowedRole() {
		return allowedRole;
	}
	public void setAllowedRole(String allowedRole) {
		this.allowedRole = allowedRole;
	}
	
	
}
