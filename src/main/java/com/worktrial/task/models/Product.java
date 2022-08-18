package com.worktrial.task.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "label")
    private String label;

    private String description;

    private Float price;

}
