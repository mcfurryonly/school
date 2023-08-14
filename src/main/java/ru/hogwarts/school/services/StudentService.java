package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);

    }

    public Student editStudent(Student student) {

        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);

    }
    public Collection<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeStudent(int minAge, int maxAge) {

        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> fidStudentsByFaculty(long facultyId) {
        return studentRepository.findAllByFaculty_Id(facultyId);
    }


}