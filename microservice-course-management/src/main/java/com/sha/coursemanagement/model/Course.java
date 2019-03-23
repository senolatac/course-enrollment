package com.sha.coursemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "course")
public class Course implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "publish_date")
    private LocalDate publishDate;
}
