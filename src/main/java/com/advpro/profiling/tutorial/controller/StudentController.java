package com.advpro.profiling.tutorial.controller;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.service.DataSeedService;
import com.advpro.profiling.tutorial.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author muhammad.khadafi
 */

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all-student")
    public ResponseEntity<String> seedStudents() {
        // Fetch only necessary data directly from the database if possible
        List<Object[]> studentCoursesData = studentService.getAllStudentsWithCoursesOptimized();

        // Convert the list into a more efficient format (e.g., a simple comma-separated String)
        String result = studentCoursesData.stream()
                .map(data -> "Student ID: " + data[0] + ", Course ID: " + data[1])  // Assuming data[0] is studentId and data[1] is courseId
                .collect(Collectors.joining(", "));

        return ResponseEntity.ok(result);
    }

    @GetMapping("/highest-gpa")
    public ResponseEntity<String> highestGpa() {
        Optional<Student> studentWithHighestGpa = studentService.findStudentWithHighestGpa();
        return studentWithHighestGpa.map(student -> ResponseEntity.ok(student.toString()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all-student-name")
    public ResponseEntity<String> allStudentName() {
        String joinedStudentNames = studentService.joinStudentNames();
        return ResponseEntity.ok(joinedStudentNames);
    }
}

