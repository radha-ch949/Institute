package com.example.Loginregister.StudentCourse;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// RegisteredStudentRepository interface
public interface RegisteredStudentRepository extends MongoRepository<RegisteredStudent, String> {
    // Additional methods if needed
    List<RegisteredStudent> findByStudentId(String studentId);

    List<RegisteredStudent> findByInstituteKey(String instituteKey);


    void deleteByStudentIdAndCourseId(String studentId, Long courseId);
}

