package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    // Optimized method to fetch only student and course IDs
    public List<Object[]> getAllStudentsWithCoursesOptimized() {
        return studentCourseRepository.findStudentAndCourseIds();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        return studentRepository.findTopByOrderByGpaDesc();
    }

    public String joinStudentNames() {
        // Fetch only student names from the database
        List<String> studentNames = studentRepository.findAllStudentNames();

        // Use Collectors.joining() to join names into a single string
        return studentNames.stream()
                .collect(Collectors.joining(", "));
    }
}
