package com.example.modu.repository;

import com.example.modu.entity.TestElement.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
