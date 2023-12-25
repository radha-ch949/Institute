package com.example.Loginregister.Controller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndCoursesIsNotNull(String email);
}