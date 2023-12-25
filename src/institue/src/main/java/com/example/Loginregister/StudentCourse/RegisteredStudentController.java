package com.example.Loginregister.StudentCourse;

import com.example.Loginregister.Courses.Course;
import com.example.Loginregister.Courses.CourseRepository;
import com.example.Loginregister.RegisteredStudents.Student;
import com.example.Loginregister.RegisteredStudents.StudentController;
import com.example.Loginregister.RegisteredStudents.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegisteredStudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final RegisteredStudentService studentService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private RegisteredStudentRepository registeredStudentRepository;
    private RegisteredStudentService registeredStudentService;

    private String name;

    @Autowired
    public RegisteredStudentController(RegisteredStudentService studentService,RegisteredStudentRepository registeredStudentRepository
,StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.registeredStudentRepository = registeredStudentRepository;    }
    @PostMapping("/register-student")
    public ResponseEntity<RegisteredStudent> registerStudent(@RequestBody RegisteredStudent student) {
        // Add logic to save the registered student in the database using studentService
        RegisteredStudent savedStudent = studentService.registerStudent(student);

        // Calculate and update the sum of course fees for the student
        updateTotalCourseFees(savedStudent.getStudentId());


        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }


        private void updateTotalCourseFees(String studentId) {
            // Get all registered courses for the student
            List<RegisteredStudent> registeredCourses = registeredStudentRepository.findByStudentId(studentId);

            // Calculate the sum of course fees
            Double sumOfCourseFees = registeredCourses.stream()
                    .mapToDouble(course -> Double.parseDouble(course.getCourseFees()))
                    .sum();

            // Update the totalCourseFees in the Student table
            Optional<Student> optionalStudent = Optional.ofNullable(studentRepository.findByStudentId(studentId));
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                student.setTotalCourseFees(sumOfCourseFees);
                studentRepository.save(student);
            }
        }

//    @GetMapping("/registered-students")
//    public ResponseEntity<List<RegisteredStudent>> getAllRegisteredStudents() {
//        // Add logic to retrieve all registered students from the database using studentService
//        List<RegisteredStudent> registeredStudents = studentService.getAllRegisteredStudents();
//        return new ResponseEntity<>(registeredStudents, HttpStatus.OK);
//    }
        @GetMapping("/registered-students")
        public ResponseEntity<List<RegisteredStudent>> getStudentsByInstituteKey (
                @RequestParam String instituteKey
){
            try {
                List<RegisteredStudent> registeredStudents = registeredStudentRepository.findByInstituteKey(instituteKey);

                logger.info("Retrieved students for instituteKey {}: {}", instituteKey, registeredStudents);
                return new ResponseEntity<>(registeredStudents, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Error getting students for instituteKey {}", instituteKey, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/registered-students/{studentId}")
        public ResponseEntity<List<RegisteredStudent>> getRegisteredStudentsByStudentId (@PathVariable String studentId)
        {
            logger.info("Getting registered students by student ID: {}", studentId);

            List<RegisteredStudent> registeredStudents = registeredStudentRepository.findByStudentId(studentId);

            if (registeredStudents.isEmpty()) {
                throw new ResourceNotFoundException("No registered students found for student ID: " + studentId);
            }

            return ResponseEntity.ok(registeredStudents);
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegisteredStudent(@PathVariable String id) {
        try {
            // Check if the registered student exists
            if (!registeredStudentRepository.existsById(id)) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }

            // Delete the registered student
            registeredStudentRepository.deleteById(String.valueOf(id));

            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
