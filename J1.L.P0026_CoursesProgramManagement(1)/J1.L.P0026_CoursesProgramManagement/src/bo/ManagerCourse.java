/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Course;
import entity.Topic;
import entity.TypeCourse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author win
 */
public class ManagerCourse {

    private List<Course> list;

    public ManagerCourse() {
        list = new ArrayList<>();
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    public boolean isDuplicate(String code) {
        for (Course course : list) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Course course) throws Exception {
        if (isDuplicate(course.getCourseCode())) {
            throw new Exception("Code Course is duplicate! Try again!");
        }
        return list.add(course);
    }

    public Course getCourseActive(String code) {
        for (Course course : list) {
            if (course.getCourseCode().equalsIgnoreCase(code)
                    && course.isActive()) {
                return course;
            }
        }
        return null;
    }

    public boolean update(String code, String newName, TypeCourse newType,
            String newTitle, Date newBeginDate, Date newEndDate, double newTuitionFee,
            Topic newTopic) {
        Course course = getCourseActive(code);
        if (course == null) {
            return false;
        } else {
            System.out.println("Before Update: " + course);
        }
        if (!newName.isEmpty()) {
            course.setName(newName);
        }
        if (newType != null) {
            course.setType(newType);
        }
        if (!newTitle.isEmpty()) {
            course.setTitle(newTitle);
        }
        if (newBeginDate != null) {
            course.setBeginDate(newBeginDate);
        }
        if (newEndDate != null) {
            course.setEndDate(newEndDate);
        }
        if (newTuitionFee > 0) {
            course.setTuitionFee(newTuitionFee);
        }
        if (newTopic != null) {
            course.setTopic(newTopic);
        }
        return true;
    }

    public boolean delete(String code) {
        Course course = getCourseActive(code);
        if (course == null) {
            return false;
        } else {
            course.setActive(false);
            return true;
        }
    }

    public void sort() {
        list.sort(Comparator
        .comparing(Course::getBeginDate) );
        
    }

    public boolean isEmpty() {
        for (Course course : list) {
            if (course.isActive()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        String result = String.format("|%10s|%15s|%10s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|\n",
                "course ID", "name", "type", "title", "begin date", "end date", "tuitionFee",
                "Name TOPIC", "Status", "Leaner PASS", "Leaner FALL", "incomes");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Course course : list) {
            if (course.isActive()) {
                result += String.format("|%10s|%15s|%10s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|%15s|\n",
                        course.getCourseCode(), course.getName(), course.getType().getValue(),
                        course.getTitle(), dateFormat.format(course.getBeginDate()),
                        dateFormat.format(course.getEndDate()), course.getTuitionFee(),
                        course.getTopic().getName(), course.getStatus(),
                        course.getLearnerPass(), course.getLearnerFail(), course.getIncomes()
                );
            }
        }
        return result;
    }

    public List<Course> searchCoursesByTopicCode(String topicCode) {
        List<Course> result = new ArrayList<>();
        for (Course course : list) {
            if (course.getTopic().getTopicCode().equalsIgnoreCase(topicCode)) {
                result.add(course);
            }
        }
        return result;
    }

    public List<Course> searchCoursesByName(String name) {
        List<Course> result = new ArrayList<>();
        for (Course course : list) {
            if (course.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(course);
            }
        }
        return result;
    }

    // Hàm ghi danh sách Course vào file
    public void saveToFile(String filename) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Course course : list) {
                if (course.isActive()) {
                    writer.write(course.getCourseCode() + "," + course.getName() + ","
                            + course.getType().getMode() + "," + course.getTitle() + ","
                            + dateFormat.format(course.getBeginDate()) + "," + dateFormat.format(course.getEndDate()) + ","
                            + course.getTuitionFee() + "," + course.getTopic().getTopicCode() + ","
                            + course.getNumberMaxLearner() + "," + course.isActive());
                    writer.newLine();
                }
            }
        }
    }

    // Hàm đọc danh sách Course từ file
    public void loadFromFile(String filename, ManagerTopic managerTopic) throws IOException, ParseException {
        list.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    String courseCode = parts[0];
                    String name = parts[1];
                    TypeCourse type = TypeCourse.getTypeCourse(Integer.parseInt(parts[2]));
                    String title = parts[3];
                    Date beginDate = dateFormat.parse(parts[4]);
                    Date endDate = dateFormat.parse(parts[5]);
                    double tuitionFee = Double.parseDouble(parts[6]);
                    Topic topic = managerTopic.getTopicActive(parts[7]);
                    int numberMaxLearner = Integer.parseInt(parts[8]);
                    boolean active = Boolean.parseBoolean(parts[9]);
                    list.add(new Course(courseCode, name, type, title, beginDate,
                            endDate, tuitionFee, topic, numberMaxLearner, new ArrayList<>(), active));
                }
            }
        }
    }
}
