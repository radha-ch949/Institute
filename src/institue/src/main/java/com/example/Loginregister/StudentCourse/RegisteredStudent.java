package com.example.Loginregister.StudentCourse;


import com.example.Loginregister.Courses.Course;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
@Document(collection = "registered_students")
public class RegisteredStudent {

    @Id
    private String id; // Use String instead of Long for MongoDB

    private String name;
    private String email;
    private String instituteKey;

    public String getInstituteKey() {
        return instituteKey;
    }

    public void setInstituteKey(String instituteKey) {
        this.instituteKey = instituteKey;
    }

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_Fees")
    private String courseFees;

    public String getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(String courseFees) {
        this.courseFees = courseFees;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Column(name="batch")
    private  String batch;
    @Column(name = "course_Duration")
    private String courseDuration;
    @Column(name = "student_id")
    private String studentId;

    public Double getMobile() {
        return mobile;
    }

    public void setMobile(Double mobile) {
        this.mobile = mobile;
    }

    private Double mobile;

    public RegisteredStudent() {
        // Default constructor required for MongoDB
    }

    public RegisteredStudent(String instituteKey,String name, String email, String courseName, Double mobile, String courseId, String courseFees, String courseDuration, String studentId, String batch) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.courseId=courseId;
        this.courseName = courseName;
        this.courseFees = courseFees;
        this.courseDuration = courseDuration;
        this.studentId = studentId;
        this.batch = batch;
        this.instituteKey = instituteKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "course_name", referencedColumnName = "name"),
            @JoinColumn(name = "course_id", referencedColumnName = "id"),
            @JoinColumn(name = "course_Fees", referencedColumnName = "fees"),
            @JoinColumn(name = "course_Duration", referencedColumnName = "duration"),
            @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
            @JoinColumn(name = "batch", referencedColumnName = "batch")


    })
    private Course course;

    public void setSumOfCourseFees(double sumOfFees) {
    }
}
