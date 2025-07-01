package com.LeaveManagement.repository;

import com.LeaveManagement.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LeaveRepository extends JpaRepository<Leave, UUID> {
    List<Leave> findByUserId(UUID userId);
}
