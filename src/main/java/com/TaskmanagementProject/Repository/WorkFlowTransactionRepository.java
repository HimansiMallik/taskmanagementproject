package com.TaskmanagementProject.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.TaskmanagementProject.Entity.WorkFlowTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkFlowTransactionRepository extends JpaRepository<WorkFlowTransaction, Long> {

    @Query("SELECT t FROM WorkFlowTransaction t WHERE t.workflow.id = :workflowId AND t.fromStatus = :fromStatus")
    List<WorkFlowTransaction> findByWorkIdFromStatus(@Param("workflowId") Long workflowId,
                                                     @Param("fromStatus") String fromStatus);
}
