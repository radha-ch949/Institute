package com.example.Loginregister.RegisteredStudents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicLong;

@Document(collection = "students")
public class Student {

    private static final AtomicLong studentCounter = new AtomicLong(0);

    @Id
    private String id; // Use String instead of Long for MongoDB

    private String studentId;
    private String instituteKey;
    private String name;
    private String email;
    private Double mobile;
    private Double totalFees;

    private Double balanceFees;

    private Double totalCourseFees;


    // Constructors, getters, setters, and other methods...
    private Double paidFees;

    public Double getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(Double paidFees) {
        this.paidFees = paidFees;
    }



    public Student() {
        // Default constructor required for MongoDB
        this.studentId = generateStudentId();
        // Set default values for fees
        this.paidFees=0.0;
        this.balanceFees = 0.0;
        this.totalCourseFees = 0.0; // Initialize total course fees
    }

    public Double getTotalCourseFees() {
        return totalCourseFees;
    }

    public void setTotalCourseFees(Double totalCourseFees) {
        this.totalCourseFees = totalCourseFees;
    }
    public Student(String name, String email, Double mobile, String instituteKey) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.studentId = generateStudentId();
        this.instituteKey = instituteKey;
        this.totalCourseFees = 0.0; // Set default values for fees

        this.balanceFees = 0.0;
    }

    private String generateStudentId() {
        long counterValue = studentCounter.incrementAndGet();
        return String.format("STUD%04d", counterValue);
    }

    // Getters and setters

    // ... existing getters and setters ...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInstituteKey() {
        return instituteKey;
    }

    public void setInstituteKey(String instituteKey) {
        this.instituteKey = instituteKey;
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

    public Double getMobile() {
        return mobile;
    }

    public void setMobile(Double mobile) {
        this.mobile = mobile;
    }

    public Double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(Double totalFees) {
        this.totalFees = totalFees;
    }



    public Double getBalanceFees() {
        return balanceFees;
    }

    public void setBalanceFees(Double balanceFees) {
        this.balanceFees = balanceFees;
    }

// Student.java




}
