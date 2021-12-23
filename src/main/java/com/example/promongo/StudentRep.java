package com.example.promongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRep extends MongoRepository<Student, String> {

    Optional<Student> findStudentsByEmail(String email);

}
