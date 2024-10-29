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
public enum TypeTopic {
    LONG_TERM(1,"long term"), SHORT_TERM(2,"short term");
    private int mode;
    private String value;

    private TypeTopic(int mode,String value) {
        this.mode = mode;
        this.value = value;
    }

    public static TypeTopic getTypeTopic(int mode) {
        switch (mode) {
            case 1:
                return LONG_TERM;
            case 2:
                return SHORT_TERM;
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
