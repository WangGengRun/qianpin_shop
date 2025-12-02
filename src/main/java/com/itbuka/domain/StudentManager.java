package com.itbuka.domain;// StudentManager.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentManager {

    private final Student student;

    // 构造器注入
    @Autowired
    public StudentManager(Student student) {
        this.student = student;
        student.setId(1);
        student.setName("王五");

    }

    public void show() {
        System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
    }
}