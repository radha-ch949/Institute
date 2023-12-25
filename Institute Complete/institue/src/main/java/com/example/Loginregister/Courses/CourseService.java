package com.example.Loginregister.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
      //  course.setAvailableSeats(course. getStrengthOfStudents());
        // Additional validation or processing if needed
        return courseRepository.save(course);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesByAdmin(String instituteKey) {
        // Add logic to retrieve courses associated with the specified admin
        return courseRepository.findByInstituteKey(instituteKey);
    }

    public List<Course> getAllCoursesByInstituteKey(String instituteKey) {
        return courseRepository.findByInstituteKey(instituteKey);
    }

    public Course getCourseById(String id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    // New method to get courses by instituteKey
    public List<Course> getCoursesByInstituteKey(String instituteKey) {
        return courseRepository.findByInstituteKey(instituteKey);
    }

}
