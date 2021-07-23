package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Subject;

public interface SubjectService {

    void addSubject(Subject subject);

    void deleteSubject(Subject subject);

    List<Subject> getAllSubjects();
    
    Subject findOne(long id);
    
    void updateSubject(Subject subject);
}
