package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="subject")
@Setter
@Getter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marks;
    private String name;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
