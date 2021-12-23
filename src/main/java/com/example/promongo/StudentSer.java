package com.example.promongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service

public class StudentSer {
    private final StudentRep studentRep;

    public List<Student> getAllStudents() {
        return studentRep.findAll();
    }
}
