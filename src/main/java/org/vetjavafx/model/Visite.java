package org.vetjavafx.model;

import java.util.Date;

public class Visite {

    private String date;
    private String description;


    public Visite(String date, String description) {
        this.date = date;
        this.description = description;
    }
    public Visite() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}















