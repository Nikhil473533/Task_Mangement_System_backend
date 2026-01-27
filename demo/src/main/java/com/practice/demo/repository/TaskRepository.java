package com.practice.demo.repository;


import com.practice.demo.beans.Task;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom{

    List<Task> findByDeletedFalse();

    Optional<Task> findByIdAndDeletedFalse(Long id);
    
}
