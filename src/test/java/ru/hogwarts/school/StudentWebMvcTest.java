package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controllers.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentWebMvcTest {


    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception{
        var responce = this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class);
        Assertions.assertThat(responce).isNotNull();
    }
    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setAge(20);
        student.setName("Jo");
        var actual = this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        Assertions.assertThat(actual.getAge()).isEqualTo(20);
        Assertions.assertThat(actual.getName()).isEqualTo("Jo");
    }

    @Test
    public void testGetStudent() throws Exception {
        var res = this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class);
        Assertions.assertThat(res).isNotEmpty();
    }

    @Test
    public void testPutStudent() throws Exception{
        Student student = new Student();
        HttpEntity<Student> httpStudent = new HttpEntity<>(student);
        student.setAge(33);
        student.setName("Ron");
        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/student", HttpMethod.PUT,
                httpStudent,
                Student.class);
        Assertions.assertThat(studentResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }
}
