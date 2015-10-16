package com.exadel.training.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Indexed
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "tagList")
    private List<Training> trainingList;

    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String specialty;

    private String colour;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> training) {
        this.trainingList = training;
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
