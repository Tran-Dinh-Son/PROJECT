/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.Course;
import entity.Learner;
import entity.Topic;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller control = new Controller();
        control.loadDataFromFile();
        do {
            int choice = Validator.getInt("===============Courses Program Management=============\n"
                    + "1. Manage the Topics\n"
                    + "2. Manage the Course\n"
                    + "3. Manage the Learner\n"
                    + "4. Search information\n"
                    + "5. Save Topics, Courses and Learner to file\n"
                    + "6. Exit program\n"
                    + "Enter your choice: ",
                    "Just be 1->6", "Invalid!", 1, 6);
            switch (choice) {
                case 1:
                    do {
                        int choice1 = Validator.getInt("===============Manage the Topics==============\n"
                                + "1.1. Add Topics to catalog\n"
                                + "1.2. Update Topic\n"
                                + "1.3. Delete Topic\n"
                                + "1.4. Display all Topics\n"
                                + "1.5. Return main menu\n"
                                + "Enter your choice: ",
                                "Just be 1->5", "Invalid!", 1, 5);
                        if (choice1 == 5) {
                            break;
                        }
                        switch (choice1) {
                            case 1:
                                do {
                                    try {
                                        Topic topic = control.addTopic();
                                        System.out.println("Add success: " + topic);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want to continue? (Y or N): ",
                                            "Just be Y or N", "[YNyn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                try {
                                    Topic topic = control.updateTopic();
                                    System.out.println("After Update: " + topic);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    Topic topic = control.deleteTopic();
                                    System.out.println("Delete success: " + topic);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    control.displayAllTopic();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (true);
                    break;
                case 2:
                    do {
                        int choice2 = Validator.getInt("===============Manage the Course==============\n"
                                + "2.1. Add Course\n"
                                + "2.2. Update Course\n"
                                + "2.3. Delete Course\n"
                                + "2.4. Display Course information\n"
                                + "2.5. Display all Course\n"
                                + "2.6. Return main menu\n"
                                + "Enter your choice: ",
                                "Just be 1->6", "Invalid!", 1, 6);
                        if (choice2 == 6) {
                            break;
                        }
                        switch (choice2) {
                            case 1:
                                do {
                                    try {
                                        Course course = control.addCourse();
                                        System.out.println("Add success: " + course);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want to continue? (Y or N): ",
                                            "Just be Y or N", "[YNyn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                try {
                                    Course course = control.updateCourse();
                                    System.out.println("After Update: " + course);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    Course course = control.deleteCourse();
                                    System.out.println("Delete success: " + course);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    control.displayCourseInformation();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 5:
                                try {
                                    control.displayAllCourse();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (true);
                    break;
                case 3:
                    do {
                        int choice3 = Validator.getInt("===============Manage the Learner==============\n"
                                + "3.1. Add Learner to Course\n"
                                + "3.2. Enter scores for learners\n"
                                + "3.3. Display Learner information\n"
                                + "3.4. Return main menu\n"
                                + "Enter your choice: ",
                                "Just be 1->4", "Invalid!", 1, 4);
                        if (choice3 == 4) {
                            break;
                        }
                        switch (choice3) {
                            case 1:
                                do {
                                    try {
                                        Learner learner = control.addLearner();
                                        System.out.println("Add success: " + learner);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want to continue? (Y or N): ",
                                            "Just be Y or N", "[YNyn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                } while (true);
                                break;

                            case 2:
                                try {
                                    control.updateScoreLeaner();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    control.displayAllLearner();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (true);
                    break;
                case 4:
                    do {
                        int choice4 = Validator.getInt("===============Search information==============\n"
                                + "4.1. Search Topic\n"
                                + "4.2. Search Course\n"
                                + "4.3. Return main menu\n"
                                + "Enter your choice: ",
                                "Just be 1->3", "Invalid!", 1, 3);
                        if (choice4 == 3) {
                            break;
                        }
                        switch (choice4) {
                            case 1:
                                control.searchTopic();
                                break;
                            case 2:
                                do {
                                    int choice4_2 = Validator.getInt("===============Search Course==============\n"
                                            + "4.2.1. By Topic\n"
                                            + "4.2.2. By Name\n"
                                            + "4.2.3. Return before menu\n"
                                            + "Enter your choice: ",
                                            "Just be 1->3", "Invalid!", 1, 3);
                                    if (choice4_2 == 3) {
                                        break;
                                    }
                                    switch (choice4_2) {
                                        case 1:
                                            control.searchCourseByTopicCode();
                                            break;
                                        case 2:
                                            control.searchCourseByName();
                                            break;
                                    }
                                } while (true);
                                break;
                        }
                    } while (true);
                    break;
                case 5:
                    control.saveDataToFile();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

}
