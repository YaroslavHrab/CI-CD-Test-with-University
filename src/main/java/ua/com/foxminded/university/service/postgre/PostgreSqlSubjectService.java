package ua.com.foxminded.university.service.postgre;

import static ua.com.foxminded.university.util.Validator.checkIfObjectNull;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.dao.SubjectDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Subject;
import ua.com.foxminded.university.service.SubjectService;
import ua.com.foxminded.university.util.exceptions.SubjectNotFoundException;

@Service
@RequiredArgsConstructor
public class PostgreSqlSubjectService implements SubjectService {

    @NonNull
    private TeacherDao teacherDao;
    @NonNull
    private SubjectDao subjectDao;
    
    @Override
    public void addSubject(Subject subject) {
        checkIfObjectNull(subject);
        subjectDao.save(subject);
    }

    @Override
    public void deleteSubject(Subject subject) {
        checkIfObjectNull(subject);
        subjectDao.delete(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.findAll();
    }

    @Override
    public Subject findOne(long id) {
        return subjectDao.findById(id).orElseThrow(()-> new SubjectNotFoundException(id));
    }

    @Override
    public void updateSubject(Subject subject) {
        checkIfObjectNull(subject);
        subjectDao.save(subject);
    }
}
