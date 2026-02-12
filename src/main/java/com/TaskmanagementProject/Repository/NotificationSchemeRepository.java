package com.TaskmanagementProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Taskmanagement.Enum.NotificationEvent;
import com.TaskmanagementProject.Entity.NotificationScheme;

@Repository
public interface NotificationSchemeRepository extends JpaRepository<NotificationScheme,Long> {
	Optional<NotificationScheme>findByProjectIdAndEventType(Long projectId,NotificationEvent eventType);

}

