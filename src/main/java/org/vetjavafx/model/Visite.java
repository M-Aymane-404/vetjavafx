package org.vetjavafx.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Visite implements Serializable {
    private static final long serialVersionUID = 1L;

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

    // Property methods for JavaFX binding
    public StringProperty dateProperty() {
        return new SimpleStringProperty(date);
    }

    public StringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }
}















