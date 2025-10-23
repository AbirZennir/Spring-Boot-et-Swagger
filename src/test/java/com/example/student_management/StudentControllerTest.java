package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        Student s = new Student();
        s.setId(1);
        s.setNom("Mido");
        s.setPrenom("Ali");

        when(studentService.save(any(Student.class))).thenReturn(s);

        ResponseEntity<Student> res = studentController.save(s);
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals("Mido", res.getBody().getNom());
    }

    @Test
    void testDeleteStudent() {
        when(studentService.delete(1)).thenReturn(true);
        ResponseEntity<Void> res = studentController.delete(1);
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
    }

    @Test
    void testFindAllStudents() {
        when(studentService.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        ResponseEntity<List<Student>> res = studentController.findAll();
        assertEquals(2, res.getBody().size());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);
        ResponseEntity<Long> res = studentController.countStudent();
        assertEquals(10L, res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void testFindByYear() {
        when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());
        ResponseEntity<Collection<Object[]>> res = studentController.findByYear();
        assertEquals(0, res.getBody().size());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
