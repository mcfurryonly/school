package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        logger.debug("Вызов конструктора FacultyService");
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Вызов метода createFaculty");

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.debug("Вызов метода findFaculty");
        return facultyRepository.findById(id).orElse(null);

    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Вызов метода editFaculty");

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Вызов метода deleteFaculty");
        facultyRepository.deleteById(id);

    }
    public Collection<Faculty> getAllFacults() {
        logger.debug("Вызов метода getAllFacults");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String name, String color) {
        logger.debug("Вызов метода findByColor");
        return facultyRepository.findAllByColorOrNameIgnoreCase(name, color);

    }

    public String longestName() {
       return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }
}
