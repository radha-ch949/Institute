package com.example.Loginregister.RegisteredStudents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    // You can add custom query methods here if needed
    Student findByStudentId(String studentId);

    List<Student> findByInstituteKey(String instituteKey);


}
