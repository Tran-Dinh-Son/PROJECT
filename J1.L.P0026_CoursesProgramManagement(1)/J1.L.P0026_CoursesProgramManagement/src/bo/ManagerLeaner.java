/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Course;
import entity.Learner;
import entity.Topic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author win
 */
public class ManagerLeaner {

    private List<Learner> list;
    private String lastID;

    public ManagerLeaner() {
        list = new ArrayList<>();
    }

    public List<Learner> getList() {
        return list;
    }

    public void setList(List<Learner> list) {
        this.list = list;
    }

    public boolean add(Learner learner) throws Exception {
        if (list.isEmpty()) {
            lastID = "L001";
        } else {
            String lastIDLeaner = list.get(list.size() - 1).getLearnerCode();
            int number = Integer.parseInt(lastIDLeaner.substring(1));
            lastID = String.format("L%03d", number + 1);
        }
        learner.setLearnerCode(lastID);
        return list.add(learner);
    }

    public Learner getLeaner(String code) {
        for (Learner learner : list) {
            if (learner.getLearnerCode().equalsIgnoreCase(code)) {
                return learner;
            }
        }
        return null;
    }

    public boolean updateScoreLearner(String code, double score) {
        Learner leaner = getLeaner(code);
        if (leaner == null) {
            return false;
        } else {
            System.out.println("Before Update: " + leaner);
            if (score <= 10 && score >= 0) {
                leaner.setScore(score);
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (Learner learner : list) {
            if (learner.isActive()) {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String title = String.format("|%15s|%15s|%15s|%15s|%15s|%15s|\n", "Learner code", "Name", "Date Of Birth",
                "Score", "Course Name", "Status");
        for (Learner learner : list) {
            if (learner.isActive()) {
                title += String.format("|%15s|%15s|%15s|%15.2f|%15s|%15s|\n", learner.getLearnerCode(), learner.getName(),
                        dateFormat.format(learner.getDateOfBirth()), learner.getScore(),
                        learner.getCourse().getName(), learner.getStatus());
            }
        }
        return title;
    }

    // Hàm ghi danh sách Learner vào file
    public void saveToFile(String filename) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Learner learner : list) {
                writer.write(learner.getLearnerCode() + "," + learner.getName() + ","
                        + dateFormat.format(learner.getDateOfBirth()) + "," + learner.getScore() + ","
                        + learner.getCourse().getCourseCode() + "," + learner.isActive());
                writer.newLine();
            }
        }
    }

    // Hàm đọc danh sách Learner từ file
    public void loadFromFile(String filename, ManagerCourse managerCourse) throws Exception {
        list.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String learnerCode = parts[0];
                    String name = parts[1];
                    Date dateOfBirth = dateFormat.parse(parts[2]);
                    double score = Double.parseDouble(parts[3]);
                    Course course = managerCourse.getCourseActive(parts[4]);
                    boolean active = Boolean.parseBoolean(parts[5]);
                    Learner learner = new Learner(learnerCode, name, dateOfBirth, score, course, active);
                    list.add(learner);
                    course.addLearner(learner);
                }
            }
        }
    }
}
