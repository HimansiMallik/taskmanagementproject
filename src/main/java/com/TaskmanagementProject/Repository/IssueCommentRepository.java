package com.TaskmanagementProject.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskmanagementProject.Entity.IssueComent;



@Repository
public interface IssueCommentRepository extends JpaRepository<IssueComent,Long>{
	
	List<IssueComent>findByIssueIdOrderByCreatedAt(Long issueId);

}
