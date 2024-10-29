/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Topic;
import entity.TypeTopic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author win
 */
public class ManagerTopic {

    private List<Topic> list;

    public ManagerTopic() {
        list = new ArrayList<>();
    }

    public void setList(List<Topic> list) {
        this.list = list;
    }

    public boolean isDuplicate(String code) {
        for (Topic topic : list) {
            if (topic.getTopicCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Topic topic) throws Exception {
        if (isDuplicate(topic.getTopicCode())) {
            throw new Exception("Code Topic is duplicate! Try again!");
        }
        return list.add(topic);
    }

    public Topic getTopicActive(String code) {
        for (Topic topic : list) {
            if (topic.getTopicCode().equalsIgnoreCase(code)
                    && topic.isActive()) {
                return topic;
            }
        }
        return null;
    }

    public boolean update(String code, String newName, TypeTopic newType,
            String newTitle, String newDuration) {
        Topic topic = getTopicActive(code);
        if (topic == null) {
            return false;
        } else {
            System.out.println("Before Update: " + topic);
        }
        if (!newName.isEmpty()) {
            topic.setName(newName);
        }
        if (newType != null) {
            topic.setType(newType);
        }
        if (!newTitle.isEmpty()) {
            topic.setTitle(newTitle);
        }
        if (!newDuration.isEmpty()) {
            topic.setDuration(newDuration);
        }
        return true;
    }

    public boolean delete(String code) {
        Topic topic = getTopicActive(code);
        if (topic == null) {
            return false;
        } else {
            topic.setActive(false);
            return true;
        }
    }

    public void sort() {
        Collections.sort(list);
    }

    public boolean isEmpty() {
        for (Topic topic : list) {
            if (topic.isActive()) {
                return false;
            }
        }
        return true;
    }

    public List<Topic> searchTopicsByName(String name) {
        List<Topic> result = new ArrayList<>();
        for (Topic topic : list) {
            if (topic.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(topic);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        String result = String.format("|%15s|%15s|%15s|%15s|%15s|\n", "topic code",
                "name", "type", "title", "duration");
        for (Topic topic : list) {
            if (topic.isActive()) {
                result += String.format("|%15s|%15s|%15s|%15s|%15s|\n", topic.getTopicCode(), topic.getName(),
                        topic.getType().getValue(), topic.getTitle(), topic.getDuration());
            }
        }
        return result;
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Topic topic : list) {
                    writer.write(topic.getTopicCode() + "," + topic.getName() + ","
                            + topic.getType().getMode() + "," + topic.getTitle() + ","
                            + topic.getDuration() + "," + topic.isActive());
                    writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String topicCode = parts[0];
                    String name = parts[1];
                    TypeTopic type = TypeTopic.getTypeTopic(Integer.parseInt(parts[2]));
                    String title = parts[3];
                    String duration = parts[4];
                    boolean active = Boolean.parseBoolean(parts[5]);
                    list.add(new Topic(topicCode, name, type, title, duration, active));
                }
            }
        }
    }

    public List<Topic> getList() {
        return list;
    }
}
