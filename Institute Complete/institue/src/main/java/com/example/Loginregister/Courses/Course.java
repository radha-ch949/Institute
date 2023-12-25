package com.example.Loginregister.Courses;
import com.example.Loginregister.Controller.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Document(collection = "courses")
public class Course {

    @Id
    private String id;
    private String name;
    private String fees;
    private String duration;
    private String batch;

    private String instituteKey;


    private int availableSeats;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // to prevent infinite loop in JSON serialization
    private User user;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStrengthOfStudents() {
        return strengthOfStudents;
    }

    public void setStrengthOfStudents(String strengthOfStudents) {
        this.strengthOfStudents = strengthOfStudents;
    }

    private String strengthOfStudents;


    // Existing methods...

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }


    public Course() {
        // default constructor
    }

    public Course(String id, String name, String fees,  String duration, String batch, String strengthOfStudents, String instituteKey) {
        this.id = id;
        this.name = name;
        this.fees = fees;
        this.duration = duration;
        this.batch = batch;
        this.strengthOfStudents = strengthOfStudents;
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

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getInstituteKey() {
        return instituteKey;
    }

    public void setInstituteKey(String instituteKey) {
        this.instituteKey = instituteKey;
    }


}
