package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query to fetch only student names
    @Query("SELECT s.name FROM Student s")
    List<String> findAllStudentNames();

    // Custom method to find the student with the highest GPA
    Optional<Student> findTopByOrderByGpaDesc();
}
