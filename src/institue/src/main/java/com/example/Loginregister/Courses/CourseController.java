//package com.example.Loginregister.Courses;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//public class CourseController {
//
//    private final CourseService courseService;
//    @Autowired
//    public CourseController(CourseService courseService) {
//        this.courseService = courseService;
//
//    }
//
//
//    @PostMapping("/add-course")
//    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
//        // Add logic to save the course in the database using courseService
//        Course savedCourse = courseService.saveCourse(course);
//        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
//    }
////    @GetMapping("/courses")
////    public ResponseEntity<List<Course>> getAllCourses() {
////        List<Course> courses = courseService.getAllCourses();
////        return new ResponseEntity<>(courses, HttpStatus.OK);
////    }
////@GetMapping("/courses")
////public ResponseEntity<List<Course>> getAllCoursesByAdmin(@RequestParam String instituteKey) {
////    List<Course> courses = courseService.getAllCoursesByAdmin(instituteKey);
////    return new ResponseEntity<>(courses, HttpStatus.OK);
////}
//
//    @GetMapping("/courses")
//    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String instituteKey) {
//        if (instituteKey != null) {
//            // If instituteKey is provided, filter courses based on the admin details
//            List<Course> courses = courseService.getCoursesByInstituteKey(instituteKey);
//            return new ResponseEntity<>(courses, HttpStatus.OK);
//        } else {
//            // If instituteKey is not provided, return all courses
//            List<Course> courses = courseService.getAllCourses();
//            return new ResponseEntity<>(courses, HttpStatus.OK);
//        }
//    }

package com.example.Loginregister.Courses;

import com.example.Loginregister.Controller.Admin;
import com.example.Loginregister.Controller.AdminRepository;
import com.example.Loginregister.Controller.User;
import com.example.Loginregister.Controller.UserRepository;
import com.example.Loginregister.RegisteredStudents.Student;
import com.example.Loginregister.RegisteredStudents.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private String name;

    @Autowired
    public CourseController(CourseService courseService, AdminRepository adminRepository, UserRepository userRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add-course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        // Add logic to save the course in the database using courseService
        Course savedCourse = courseService.saveCourse(course);
        // Reduce available seats by 1

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCoursesByInstituteKey(@RequestParam String email) {
        // Fetch admin details based on the email
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null) {
            // Admin found, fetch courses for the institute key
            List<Course> courses = courseService.getCoursesByInstituteKey(admin.getInstituteKey());
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            // Admin not found for the provided email
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/courses/user")
    public ResponseEntity<List<Course>> getCoursesInstituteKey(@RequestParam String email) {
        // Fetch admin details based on the email
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // Admin found, fetch courses for the institute key
            List<Course> courses = courseService.getCoursesByInstituteKey(user.getInstituteKey());
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            // Admin not found for the provided email
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // In your controller or service class


//    @GetMapping("/courses/user")
//    public ResponseEntity<List<Course>> getCoursesByUserEmail(@RequestParam String email) {
//        // Fetch user details based on the email
//        User user = userRepository.findByEmail(email);
//
//        if (user != null) {
//            // User found, fetch courses for the institute key (assuming instituteKey is a field in the User entity)
//            List<Course> courses = courseService.getCoursesByInstituteKey(user.getInstituteKey());
//            return new ResponseEntity<>(courses, HttpStatus.OK);
//        } else {
//            // User not found for the provided email
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }



    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Optional<Course> courseOptional = Optional.ofNullable(courseService.getCourseById(id));

        return courseOptional.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
