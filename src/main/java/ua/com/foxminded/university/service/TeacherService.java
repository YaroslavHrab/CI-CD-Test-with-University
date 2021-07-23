package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Teacher;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();
    
    void updateTeacher(Teacher teacher);
    
    Teacher findOne(long id);
}
