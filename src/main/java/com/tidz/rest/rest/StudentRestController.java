package com.tidz.rest.rest;

import com.tidz.rest.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Mano", "Maneiro"));
        students.add(new Student("Mana", "Maneiro"));
        students.add(new Student("Truta", "Maneiro"));
        return students;
    }
}
