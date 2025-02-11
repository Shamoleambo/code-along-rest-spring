package com.tidz.rest.rest;

import com.tidz.rest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {
        this.students = new ArrayList<>();
        students.add(new Student("Mano", "Maneiro"));
        students.add(new Student("Mana", "Maneiro"));
        students.add(new Student("Truta", "Maneiro"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return this.students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        if (id >= this.students.size() || id < 0) {
            throw new StudentNotFoundException("Student id not found - " + id);
        }
        return this.students.get(id);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
