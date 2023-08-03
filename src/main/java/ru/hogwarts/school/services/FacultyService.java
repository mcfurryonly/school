package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> facults = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facults.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facults.get(id);

    }

    public Faculty editFaculty(Faculty faculty) {
        if (facults.containsKey(faculty.getId())) {
            facults.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return facults.remove(id);

    }
    public Collection<Faculty> getAllFacults() {
        return facults.values();
    }

    public Collection<Faculty> findByColor(String color) {
        return facults.values().stream().filter(it -> Objects.equals(it.getColor(), color)).collect(Collectors.toList());

    }
}
