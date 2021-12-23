package com.example.promongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentCon {
    private final StudentSer studentser;
    @GetMapping
    public List<Student> fetchAllStudents(){
        return studentser.getAllStudents();
    }
}
