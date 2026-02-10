package com.example.question2_student_api;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    public StudentController() {
        students.put(nextId, new Student(nextId++, "John", "Doe", "john.doe@email.com", "Computer Science", 3.8));
        students.put(nextId, new Student(nextId++, "Jane", "Smith", "jane.smith@email.com", "Computer Science", 3.5));
        students.put(nextId, new Student(nextId++, "Bob", "Johnson", "bob.johnson@email.com", "Mathematics", 2.9));
        students.put(nextId, new Student(nextId++, "Alice", "Williams", "alice.williams@email.com", "Computer Science", 3.9));
        students.put(nextId, new Student(nextId++, "Charlie", "Brown", "charlie.brown@email.com", "Physics", 3.2));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return students.get(studentId);
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        return students.values().stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Student> filterStudentsByGpa(@RequestParam Double gpa) {
        return students.values().stream()
                .filter(s -> s.getGpa() >= gpa)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.put(student.getStudentId(), student);
        return student;
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        student.setStudentId(studentId);
        students.put(studentId, student);
        return student;
    }
}
