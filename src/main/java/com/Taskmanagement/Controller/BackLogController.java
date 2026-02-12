package com.Taskmanagement.Controller;

import java.util.List;
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

import com.Taskmanagement.Service.BackLogService;
import com.TaskmanagementProject.Entity.Issue;

@RestController
@RequestMapping("/api")
public class BackLogController {

    @Autowired
    private BackLogService backLogService;
    
    @GetMapping("/backLog") 
    public ResponseEntity<?> getBacklog() {
        return ResponseEntity.ok("Success!");
    }
    // Fixed: Added closing brace }
    @GetMapping("/{projectId}")
    public ResponseEntity<List<Issue>> getBackLog(@PathVariable Long projectId) {
        return ResponseEntity.ok(backLogService.getBackLog(projectId));
    }

    // Fixed: Moved / inside the brace
    @PostMapping("/{projectId}/record")
    public ResponseEntity<String> record(@PathVariable Long projectId, @RequestBody List<Long> orderIssueId) {
        backLogService.recordBackLog(projectId, orderIssueId);
        return ResponseEntity.ok("BackLog recorded");
    }
    
    // Fixed: Cleaned hidden characters in the parameters
    @PutMapping("/add_to-sprint/{issueId}/{sprintId}")
    public ResponseEntity<Issue> addIssueToSprint(@PathVariable Long issueId, @PathVariable Long sprintId) {
        return ResponseEntity.ok(backLogService.addIssueToSprint(issueId, sprintId));
    }

    // Fixed: Removed double opening brace {{
    @GetMapping("/{projectId}/hierarchy")
    public ResponseEntity<Map<String, Object>> getHierarchy(@PathVariable Long projectId) {
        // Now the return types match: Map<String, Object>
        return ResponseEntity.ok(backLogService.getBackLogHierarchy(projectId));
    }
}