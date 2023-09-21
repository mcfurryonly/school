package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {
    public Faculty() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String color;
    private String name;
    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Faculty(Long id, String color, String name) {
        this.id = id;
        this.color = color;
        this.name = name;
    }
}
