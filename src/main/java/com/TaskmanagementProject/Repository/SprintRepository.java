package com.TaskmanagementProject.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Taskmanagement.Enum.SprintState;
import com.TaskmanagementProject.Entity.Sprint;



@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long>{
List<Sprint>findByProjectId(Long projectId);
List<Sprint>findByState(SprintState state);
	
}
