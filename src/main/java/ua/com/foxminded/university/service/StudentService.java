package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Student;

public interface StudentService {

    void addStudent(Student student);

    void deleteStudent(Student student);

    List<Student> getAllStudents();
    
    Student findOne(long id);
    
    void updateStudent(Student student);
}
