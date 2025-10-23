package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public boolean delete(int id) {
        Optional<Student> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.delete(opt.get());
            return true;
        }
        return false;
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public long countStudents() {
        return repo.count();
    }

    public Collection<Object[]> findNbrStudentByYear() {
        return repo.findNbrStudentByYear();
    }
}
