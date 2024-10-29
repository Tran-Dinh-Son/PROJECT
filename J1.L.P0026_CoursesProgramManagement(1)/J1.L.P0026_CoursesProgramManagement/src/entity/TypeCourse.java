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
public enum TypeCourse {
    ON(1,"Online"), OFF(2,"Offline");
    private int mode;
    private String value;

    private TypeCourse(int mode,String value) {
        this.mode = mode;
        this.value = value;
    }

    public static TypeCourse getTypeCourse(int mode) {
        switch (mode) {
            case 1:
                return ON;
            case 2:
                return OFF;
            default:
                throw new AssertionError();
        }
    }

    public int getMode() {
        return mode;
    }

    public String getValue() {
        return value;
    }
     
}
