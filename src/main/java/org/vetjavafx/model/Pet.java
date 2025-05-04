package org.vetjavafx.model;

import java.util.ArrayList;

public class Pet {
    private String name;
    private String birthDate;
    private String type;
    private ArrayList<Visite> visites = new ArrayList<>();

    public Pet(String name, String birthDate, String type) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
    }

    public Pet() {
    }

    // Update the pet's details
    public void updatePet(String updatedName, String updatedBirthDate, String updatedType) {
        this.name = updatedName;
        this.birthDate = updatedBirthDate;
        this.type = updatedType;
    }

    // Add a visit to the pet
    public void addVisite(Visite visite) {
        visites.add(visite);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Visite> getVisites() {
        return visites;
    }

    public void setVisites(ArrayList<Visite> visites) {
        this.visites = visites;
    }

    @Override
    public String toString() {
        return "Pet [name=" + name + ", birthDate=" + birthDate + ", type=" + type + "]";
    }
}
