package com.example.promongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ProMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProMongoApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(StudentRep repository, MongoTemplate mongoTemp) {
		return args -> {
			Address adrs = new Address(
					"India", "Kanpur", "208002"
			);
			String email = "dev@gmail.com";
			Student student = new Student(
					"Dev", "Yadav", "dev@gmail.com", Gender.Male, adrs,
					List.of("Computer Science", "Engineering maths", "Java"), BigDecimal.TEN, LocalDateTime.now()
			);
			repository.findStudentsByEmail(email).ifPresentOrElse(s -> {
						System.out.println(s + "already exist");
					}, () -> {
						System.out.println("Inserting student" + student);
						repository.insert(student);
					}

			);
		};

	}


	private void usingMongoTemplateAndQuery(StudentRep repository, MongoTemplate mongoTemp, String email, Student student) {


		Query query = new Query();
		query.addCriteria(Criteria.where("email").

				is(email));

		List<Student> students = mongoTemp.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException("found many students with email" + email);
		}
		if (students.isEmpty()) {
			System.out.println("Inserting Student" + student);
			repository.insert(student);
		} else {
			System.out.println(student + "already  exist");
		}

	}
}

