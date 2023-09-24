package com.example.modu.repository;

import com.example.modu.entity.TestElement.UserTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestResultRepository extends JpaRepository<UserTestResult, Long> {
}
