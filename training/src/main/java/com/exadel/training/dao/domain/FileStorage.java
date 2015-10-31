package com.exadel.training.dao.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by ayudovin on 31.10.2015.
 */
@Entity
@Table
public class FileStorage {

    @Id
    @GeneratedValue
    private long id;

    @Lob
    private byte[] file;

    @NotBlank
    private String name;

    @ManyToOne
    private Training training;

    public FileStorage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
