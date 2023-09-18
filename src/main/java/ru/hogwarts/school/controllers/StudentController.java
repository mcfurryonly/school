package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {

        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> findByAgeStudent(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findByAgeStudent(minAge, maxAge);
    }

    @GetMapping("/byFaculty")
    public Collection<Student> findStudentByFaculty(@RequestParam long facId) {
        return studentService.fidStudentsByFaculty(facId);
    }

    @GetMapping("/avg-age")
    public int averageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/count")
    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("/last")
    public Collection<Student> getLastStudent() {
        return studentService.getLastStudent();
    }

    @GetMapping("/name-A")
    public List<String> getNameStartA() {
        return studentService.getNameStartA();
    }

    @GetMapping("/avg-ageAll")
    public double getStudentAvgAge() {
        return studentService.getStudentAvgAge();
    }

    @GetMapping("print-thread")
    public void printUnsync() {
        studentService.printUnsync();
    }
    @GetMapping("print-synchronized-thread")
    public void printSync() {
        studentService.printSync();
    }
    @GetMapping("/get_integer_number")
    public int getIntegerNumber() {
        return studentService.getIntegerNumber();
    }





}
