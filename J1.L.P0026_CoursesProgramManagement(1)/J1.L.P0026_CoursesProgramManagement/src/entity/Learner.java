/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class Learner {

    private String learnerCode;
    private String name;
    private Date dateOfBirth;
    private double score;
    private Course course;
    private boolean active;
    public Learner(String learnerCode, String name, Date dateOfBirth, double score, Course course,boolean active) {
        this.learnerCode = learnerCode;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.score = score;
        this.course = course;
        this.active = active;
    }

    public Learner() {
    }

    public String getLearnerCode() {
        return learnerCode;
    }

    public void setLearnerCode(String learnerCode) {
        this.learnerCode = learnerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Learner{" + learnerCode + ", " + name + ", " + dateFormat.format(dateOfBirth) + ", " + score + ", course=" + course.getName() + '}';
    }

    public String getStatus() {
        if (score < 5) {
            return "Fail";
        } else {
            return "Pass";
        }
    }
}
