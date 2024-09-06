package com.example.demo.service;


import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudentsWithpage(int pageNo, int pagesize) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pagesize);/**

         */
        List<Student> content = studentRepository.findAll(pageRequest).getContent();
        return content;
    }
}
