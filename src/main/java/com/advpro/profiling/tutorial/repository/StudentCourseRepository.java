package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findByStudentId(Long studentId);

    // Updated query to return only studentId and courseId
    @Query("SELECT s.id, c.id FROM StudentCourse sc JOIN sc.student s JOIN sc.course c")
    List<Object[]> findStudentAndCourseIds();
}
