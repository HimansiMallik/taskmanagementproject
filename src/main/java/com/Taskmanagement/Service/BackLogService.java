package com.Taskmanagement.Service;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Taskmanagement.Enum.IssueType;
import com.TaskmanagementProject.Entity.Issue;
import com.TaskmanagementProject.Repository.IssueRepository;
import com.TaskmanagementProject.Repository.SprintRepository;

@Service
public class BackLogService {

    @Autowired
    private IssueRepository issueRepo;
    
    @Autowired
    private SprintRepository sprintRepo;
    
    public List<Issue> getBackLog(Long projectId) {
        return issueRepo.findByProjectIdAndSprintIdIsNullOrderByBackLogPosition(projectId);
    }

    @Transactional
    public void recordBackLog(Long projectId, List<Long> orderIssueId) {
        int pos = 0;
        for (Long issueId : orderIssueId) {
            Issue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found: " + issueId));
            issue.setBackLogPosition(pos++);
            issueRepo.save(issue);
        }
    }

    @Transactional
    public Issue addIssueToSprint(Long issueId, Long sprintId) {
        Issue issue = issueRepo.findById(issueId)
            .orElseThrow(() -> new RuntimeException("Issue not found: " + issueId));
        
        issue.setSprintId(sprintId);
        return issueRepo.save(issue);
    }

    public Map<String, Object> getBackLogHierarchy(Long projectId) {
        List<Issue> backLog = getBackLog(projectId);
        Map<Long, Map<String, Object>> epicMap = new LinkedHashMap<>();
        Map<Long, Long> storyToEpicMapping = new HashMap<>(); 

        // 1. Identify Epics
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.EPICS) {
                Map<String, Object> data = new LinkedHashMap<>();
                data.put("epic", i);
                data.put("stories", new ArrayList<Issue>());
                data.put("subtasks", new HashMap<Long, List<Issue>>());
                epicMap.put(i.getId(), data);
            }
        }
        
        // 2. Attach Stories and map them to their Epic
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.STORIES && i.getEpicId() != null) {
                Map<String, Object> epicData = epicMap.get(i.getEpicId());
                if (epicData != null) {
                    ((List<Issue>) epicData.get("stories")).add(i);
                    storyToEpicMapping.put(i.getId(), i.getEpicId());
                }
            }
        }
        
        // 3. Attach Subtasks using the mapping for O(1) lookup
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.SUBTASKS && i.getSourceIssueId() != null) {
                Long parentEpicId = storyToEpicMapping.get(i.getSourceIssueId());
                if (parentEpicId != null && epicMap.containsKey(parentEpicId)) {
                    Map<Long, List<Issue>> subTasksMap = (Map<Long, List<Issue>>) epicMap.get(parentEpicId).get("subtasks");
                    subTasksMap.computeIfAbsent(i.getSourceIssueId(), k -> new ArrayList<>()).add(i);
                }
            }
        }
        return Collections.singletonMap("epics", new ArrayList<>(epicMap.values()));
    }
}