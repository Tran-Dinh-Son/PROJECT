/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public class Topic implements Comparable<Topic>{
    private String topicCode;
    private String name;
    private TypeTopic type;
    private String title;
    private String duration;
    private boolean active;

    public Topic(String topicCode, String name, TypeTopic type, String title, String duration, boolean active) {
        this.topicCode = topicCode;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
        this.active = active;
    }

    public Topic() {
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeTopic getType() {
        return type;
    }

    public void setType(TypeTopic type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Topic{" + topicCode + ", " + name + ", " + type.getValue()
                + ", " + title + ", " + duration + '}';
    }

    @Override
    public int compareTo(Topic o) {
        return name.compareTo(o.name);
    }
    
    
}
