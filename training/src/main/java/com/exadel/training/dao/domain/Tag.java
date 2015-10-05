package com.exadel.training.dao.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "tagList")
    private List<Training> training;

    private String specialty;

    private String colour;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public List<Training> getTraining() {
        return training;
    }

    public void setTraining(List<Training> training) {
        this.training = training;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
