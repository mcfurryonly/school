package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        logger.debug("Вызов конструктора StudentService");
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.debug("Вызов метода createStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("Вызов метода findStudent");
        return studentRepository.findById(id).orElse(null);

    }

    public Student editStudent(Student student) {
        logger.debug("Вызов метода editStudent");

        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Вызов метода deleteStudent");
        studentRepository.deleteById(id);

    }
    public Collection<Student> getAllStudents() {
        logger.debug("Вызов метода getAllStudents");

        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeStudent(int minAge, int maxAge) {
        logger.debug("Вызов метода findByAgeStudent");

        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> fidStudentsByFaculty(long facultyId) {
        logger.debug("Вызов метода findStudentsByFaculty");
        return studentRepository.findAllByFaculty_Id(facultyId);
    }

    public int getAverageAge() {
        logger.debug("Вызов метода getAverageAge");
        return studentRepository.getAverageAge();
    }
    public int getStudentCount() {
        logger.debug("Вызов метода getStudentCount");
        return studentRepository.getStudentCount();
    }
    public List<Student> getLastStudent() {
        logger.debug("Вызов метода getLastStudent");
        return studentRepository.getLastStudents();
    }


}