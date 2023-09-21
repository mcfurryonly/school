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
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

   private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Integer count = 0;



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

    public double getStudentAvgAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    public List<String> getNameStartA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public void printUnsync() {
        var students = studentRepository.findAll();


        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
    }
    public void printSync() {

            var students = studentRepository.findAll();


            System.out.println(students.get(0));
            System.out.println(students.get(1));

            new Thread(() -> {
                print(students);
                print(students);
            }).start();

            new Thread(() -> {
                print(students);
                print(students);
            }).start();

    }

    public synchronized void print(List<Student> students) {
        System.out.println(students.get(count++ % students.size()));

    }

    public int getIntegerNumber() {
        return Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }


}