/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.ManagerCourse;
import bo.ManagerLeaner;
import bo.ManagerTopic;
import entity.Course;
import entity.Learner;
import entity.Topic;
import entity.TypeCourse;
import entity.TypeTopic;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManagerTopic topicManager;
    private ManagerCourse courseManager;
    private ManagerLeaner leanerManager;

    public Controller() {
        topicManager = new ManagerTopic();
        courseManager = new ManagerCourse();
        leanerManager = new ManagerLeaner();
    }

    public Topic addTopic() throws Exception {
        String topicCode = Validator.getString("Enter topic code: ", "Can not empty!", "^(?!\\s*$).+");
        String name = Validator.getString("Enter name: ", "Can not empty!", "^(?!\\s*$).+");
        int choiceType = Validator.getInt("===Type Of Topic===\n"
                + "1. long term\n"
                + "2. short term\n"
                + "Enter your choice: ", "Just be 1->2", "Invalid!", 1, 2);
        TypeTopic type = TypeTopic.getTypeTopic(choiceType);
        String title = Validator.getString("Enter title: ", "Can not empty!", "^(?!\\s*$).+");
        String duration = Validator.getString("Enter duration: ", "Can not empty!", "^(?!\\s*$).+");;
        boolean active = true;
        Topic topic = new Topic(topicCode, name, type, title, duration, active);
        if (topicManager.add(topic)) {
            return topic;
        } else {
            throw new Exception("Add fail!");
        }
    }

    public Topic updateTopic() throws Exception {
        String topicCode = Validator.getString("Enter topic code: ", "Can not empty!", "^(?!\\s*$).+");
        Topic topic = topicManager.getTopicActive(topicCode);
        if (topic == null) {
            throw new Exception("The topic does not exist");
        }

        String newName = Validator.getStringUpdate("Enter new name: ");
        int choiceType = Validator.getIntUpdate("===Type Of Topic===\n"
                + "1. long term\n"
                + "2. short term\n"
                + "Enter your choice: ", "Just be 1->2", "Invalid!", 1, 2);
        TypeTopic newType;
        if (choiceType == 0) {
            newType = null;
        } else {
            newType = TypeTopic.getTypeTopic(choiceType);
        }
        String newTitle = Validator.getStringUpdate("Enter new title: ");
        String newDuration = Validator.getStringUpdate("Enter new duration: ");
        if (topicManager.update(topicCode, newName, newType, newTitle, newDuration)) {
            return topic;
        } else {
            throw new Exception("Update fail!");
        }
    }

    public Topic deleteTopic() throws Exception {
        String topicCode = Validator.getString("Enter topic code: ", "Can not empty!", "^(?!\\s*$).+");
        Topic topic = topicManager.getTopicActive(topicCode);
        if (topic == null) {
            throw new Exception("The topic does not exist");
        } else {
            System.out.println(topic);
        }
        String choose = Validator.getString("Do you want to delete? (Y or N): ", "Y or N", "[YNyn]");
        if (choose.equalsIgnoreCase("Y")) {
            if (topicManager.delete(topicCode)) {
                //Khi xoa topic thi chuong trinh tu dong xoa di nhung khoa hoc va learner nam trong topic do
                List<Course> listCourse = courseManager.getList();
                for (Course course : listCourse) {
                    if (course.getTopic().getTopicCode().equals(topic.getTopicCode())) {
                        List<Learner> listLearner = course.getListLearner();
                        for (Learner learner : listLearner) {
                            learner.setActive(false); //Xoa leaner trong topic
                        }
                        course.setActive(false); //Xoa course trong topic
                    }
                }
                return topic;
            } else {
                throw new Exception("Delete fail");
            }
        } else {
            throw new Exception("Delete fail");
        }
    }

    public void displayAllTopic() throws Exception {
    List<Topic> topics = topicManager.getList();
    boolean hasShortTermTopics = false;
    for (Topic topic : topics) {
        if (topic.getType().getValue().equals(topics)) { 
            if (!hasShortTermTopics) {
                System.out.println("==========================================================================================================");
                System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Name", "Type", "Title", "Duration");
                System.out.println("==========================================================================================================");
                hasShortTermTopics = true; 
            }
            System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s |\n", 
                topic.getTopicCode(), 
                topic.getName(), 
                topic.getType().getValue(), 
                topic.getTitle(), 
                topic.getDuration());
        }
    }

    if (!hasShortTermTopics) {
        throw new Exception("No short term topics found.");
    }

    if (hasShortTermTopics) {
        System.out.println("==========================================================================================================");
    }
}
    

    public Course addCourse() throws Exception {
        String courseCode = Validator.getString("Enter course code: ", "Can not empty!", "^(?!\\s*$).+");
        String name = Validator.getString("Enter course name: ", "Can not empty!", "^(?!\\s*$).+");
        int choiceType = Validator.getInt("===Type Of Course===\n"
                + "1. Online\n"
                + "2. Offline\n"
                + "Enter your choice: ", "Just be 1->2", "Invalid!", 1, 2);
        TypeCourse type = TypeCourse.getTypeCourse(choiceType);
        String title = Validator.getString("Enter course title: ", "Can not empty!", "^(?!\\s*$).+");;
        Date beginDate = Validator.getDate("Enter begin date: ", "Just be dd/MM/yyy", "dd/MM/yyyy");
        Date endDate = Validator.getDate("Enter end date: ", "Just be dd/MM/yyyy", "dd/MM/yyyy");
        double tuitionFee = Validator.getDouble("Enter tuitionFee: ", "Just be >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        displayAllTopic();
        String topicCode = Validator.getString("Enter topic code: ", "Can not empty!", "^(?!\\s*$).+");
        Topic topic = topicManager.getTopicActive(topicCode);
        if (topic == null) {
            throw new Exception("This topic not exist!");
        }
        int numberMaxLearner = Validator.getInt("Enter max size learner in course: ", "Just be >0", "Invalid!", 1, Integer.MAX_VALUE);
        List<Learner> listLearner = new ArrayList<>();
        boolean active = true;
        Course course = new Course(courseCode, name, type, title, beginDate, endDate,
                tuitionFee, topic, numberMaxLearner, listLearner, active);
        if (courseManager.add(course)) {
            return course;
        } else {
            throw new Exception("Add fail!");
        }
    }

    public Course updateCourse() throws Exception {
        String courseCode = Validator.getString("Enter course code: ", "Can not empty!", "^(?!\\s*$).+");
        Course course = courseManager.getCourseActive(courseCode);
        if (course == null) {
            throw new Exception("The course does not exist");
        }
        String newName = Validator.getStringUpdate("Enter new name: ");
        int choiceType = Validator.getIntUpdate("===Type Of Course===\n"
                + "1. Online\n"
                + "2. Offline\n"
                + "Enter your choice: ", "Just be 1->2", "Invalid!", 1, 2);
        TypeCourse newType;
        if (choiceType == 0) {
            newType = null;
        } else {
            newType = TypeCourse.getTypeCourse(choiceType);
        }
        String newTitle = Validator.getStringUpdate("Enter new title: ");
        Date newBeginDate = Validator.getDateUpdate("Enter begin date: ", "Just be dd/MM/yyy", "dd/MM/yyyy");
        Date newEndDate = Validator.getDateUpdate("Enter end date: ", "Just be dd/MM/yyyy", "dd/MM/yyyy");
        double newTuitionFee = Validator.getDoubleUpdate("Enter tuitionFee: ", "Just be >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        displayAllTopic();
        String topicCode = Validator.getStringUpdate("Enter topic code: ");
        Topic newTopic;
        if (topicCode.isEmpty()) {
            newTopic = null;
        } else {
            newTopic = topicManager.getTopicActive(topicCode);
            if (newTopic == null) {
                throw new Exception("This topic not exist!");
            }
        }
        if (courseManager.update(courseCode, newName, newType, newTitle,
                newBeginDate, newEndDate, newTuitionFee, newTopic)) {
            return course;
        } else {
            throw new Exception("Update fail!");
        }
    }

    public Course deleteCourse() throws Exception {
        String courseCode = Validator.getString("Enter course code: ", "Can not empty!", "^(?!\\s*$).+");
        Course course = courseManager.getCourseActive(courseCode);
        if (course == null) {
            throw new Exception("The course does not exist");
        } else {
            System.out.println(course);
        }
        String choose = Validator.getString("Do you want to delete? (Y or N): ", "Y or N", "[YNyn]");
        if (choose.equalsIgnoreCase("Y")) {
            if (courseManager.delete(courseCode)) {
                //Khi xoa course , chuong trinh se tu dong xoa di nhung learner nam trong course do
                List<Learner> listLearner = course.getListLearner();
                for (Learner learner : listLearner) {
                    learner.setActive(false); //Xoa leaner trong course
                }
                return course;
            } else {
                throw new Exception("Delete fail");
            }
        } else {
            throw new Exception("Delete fail");
        }
    }

   public void displayCourseInformation() throws Exception {
    String courseCode = Validator.getString("Enter course code: ", "Cannot be empty!", "^(?!\\s*$).+");
    Course course = this.courseManager.getCourseActive(courseCode);
    if (course == null) {
        System.out.println("Cannot find course!");
    } else if (!course.getType().getValue().equals("short term")) { // Kiểm tra loại khóa học
        System.out.println("This course is not a short term course.");
    } else {
        System.out.println("==========================================================================================================");
        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Name", "Type", "Title", "Begin Date", "End Date", "Tuition Fee");
        System.out.println("==========================================================================================================");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12.2f |\n", 
//            course.getCourseCode(), 
//            course.getName(), 
            course.getType().getValue()); 
//            course.getTitle(), 
//            dateFormat.format(course.getBeginDate()), 
//            dateFormat.format(course.getEndDate()), 
//            course.getTuitionFee());
        System.out.println("==========================================================================================================");
    }
}

    public void displayAllCourse() throws Exception {
        courseManager.sort();
        String str = courseManager.toString();
        if (str == null) {
            throw new Exception("List is empty");
        } else {
            System.out.println(str);
        }
    }

    public Learner addLearner() throws Exception {
        String leanerName = Validator.getString("Enter name: ", "Can not empty!", "^(?!\\s*$).+");
        Date dob = Validator.getDate("Enter day of birth: ", "Please enter format dd/MM/yyyy", "dd/MM/yyyy");
        double score = Validator.getDouble("Enter score: ", "Just be 0->10", "Inavlid!", 0, 10);
        displayAllCourse();
        String courseCode = Validator.getString("Enter course code: ", "Can not empty!", "^(?!\\s*$).+");
        Course course = courseManager.getCourseActive(courseCode);
        if (course == null) {
            throw new Exception("This course not exist!");
        }
        Learner learner = new Learner(null, leanerName, dob, score, course, true);
        //add new learner to course
        course.addLearner(learner);
        //add leaner to list leaner
        if (leanerManager.add(learner)) {
            return learner;
        } else {
            throw new Exception("Add fail!");
        }
    }

    public void updateScoreLeaner() throws Exception {
        String leanerCode = Validator.getString("Enter Learner code: ", "Can not empty!", "^(?!\\s*$).+");
        Learner learner = leanerManager.getLeaner(leanerCode);
        if (learner == null) {
            throw new Exception("The course does not exist");
        }
        double score = Validator.getDouble("Enter score: ", "Just be 0->10", "Inavlid!", 0, 10);
        if (leanerManager.updateScoreLearner(leanerCode, score)) {
            System.out.println("Update score success: " + learner);
        } else {
            System.out.println("Update score fail");
        }
    }

    public void displayAllLearner() throws Exception {
        String str = leanerManager.toString();
        if (str == null) {
            throw new Exception("List is empty");
        } else {
            System.out.println(str);
        }
    }

    public void searchTopic() {
        String name = Validator.getString("Enter name topic: ", "Can not empty!", "^(?!\\s*$).+");
        List<Topic> result = topicManager.searchTopicsByName(name);
        if (result.isEmpty()) {
            System.out.println("Can not found");
        } else {
            ManagerTopic manager = new ManagerTopic();
            manager.setList(result);
            System.out.println(manager.toString());
        }
    }

    public void searchCourseByTopicCode() {
        String codeTopic = Validator.getString("Enter code topic: ", "Can not empty!", "^(?!\\s*$).+");
        List<Course> result = courseManager.searchCoursesByTopicCode(codeTopic);
        if (result.isEmpty()) {
            System.out.println("Can not found");
        } else {
            ManagerCourse manager = new ManagerCourse();
            manager.setList(result);
            System.out.println(manager.toString());
        }
    }

    public void searchCourseByName() {
        String name = Validator.getString("Enter name course: ", "Can not empty!", "^(?!\\s*$).+");
        List<Course> result = courseManager.searchCoursesByName(name);
        if (result.isEmpty()) {
            System.out.println("Can not found");
        } else {
            ManagerCourse manager = new ManagerCourse();
            manager.setList(result);
            System.out.println(manager.toString());
        }
    }

    public void loadDataFromFile() {
        try {
            topicManager.loadFromFile("topics.txt");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            courseManager.loadFromFile("courses.txt", topicManager);
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            leanerManager.loadFromFile("learners.txt", courseManager);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void saveDataToFile() {
        try {
            topicManager.saveToFile("topics.txt");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            courseManager.saveToFile("courses.txt");
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            leanerManager.saveToFile("learners.txt");
           
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
