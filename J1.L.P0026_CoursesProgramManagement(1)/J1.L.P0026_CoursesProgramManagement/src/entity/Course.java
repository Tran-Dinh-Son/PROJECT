/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author win
 */
public class Course implements Comparable<Course> {

    private String courseCode;
    private String name;
    private TypeCourse type;
    private String title;
    private Date beginDate;
    private Date endDate;
    private double tuitionFee;
    private Topic topic;
    private int numberMaxLearner;
    private List<Learner> listLearner;
    private boolean active;

    public Course() {
    }

    public Course(String courseCode, String name, TypeCourse type, String title,
            Date beginDate, Date endDate,
            double tuitionFee, Topic topic,
            int numberMaxLearner, List<Learner> listLearner,
            boolean active) {
        this.courseCode = courseCode;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
        this.numberMaxLearner = numberMaxLearner;
        this.listLearner = listLearner;
        this.active = active;
    }

    public boolean isDuplicateLearner(Learner learner) {
        for (Learner learner1 : listLearner) {
            if (learner1.getLearnerCode().equalsIgnoreCase(learner.getLearnerCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean addLearner(Learner learner) throws Exception {
        if (isDuplicateLearner(learner)) {
            throw new Exception("This leaner is duplicate!");
        }
        if (listLearner.size() == numberMaxLearner) {
            throw new Exception("This course is full! Can not add!");
        }
        return listLearner.add(learner);
    }

    public List<Learner> getListLearner() {
        return listLearner;
    }

    public void setListLearner(List<Learner> listLearner) {
        this.listLearner = listLearner;
    }

    public int getNumberMaxLearner() {
        return numberMaxLearner;
    }

    public void setNumberMaxLearner(int numberMaxLearner) {
        this.numberMaxLearner = numberMaxLearner;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeCourse getType() {
        return type;
    }

    public void setType(TypeCourse type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Course{" + courseCode + ", " + name + ", " + type + ", " + title + ", "
                + dateFormat.format(beginDate) + ", " + dateFormat.format(endDate) + ", " +
                tuitionFee + ", " + topic.getName() + '}';
    }

    @Override
    public int compareTo(Course o) {
        return beginDate.compareTo(o.beginDate);
    }

    public String getStatus() {
        if (listLearner.size() == numberMaxLearner) {
            return "Full";
        } else {
            return "Not Full";
        }
    }

    public int getLearnerPass() {
        int number = 0;
        for (Learner learner : listLearner) {
            if (learner.getScore() >= 5) {
                number++;
            }
        }
        return number;
    }

    public int getLearnerFail() {
        int number = 0;
        for (Learner learner : listLearner) {
            if (learner.getScore() < 5) {
                number++;
            }
        }
        return number;
    }

    public double getIncomes() {
        return listLearner.size()*tuitionFee;
    }
    
}
