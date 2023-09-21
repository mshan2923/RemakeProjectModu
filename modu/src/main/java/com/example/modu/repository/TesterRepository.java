package com.example.modu.repository;

import com.example.modu.entity.TestElement.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Long> {
}
