package ua.com.foxminded.university.service.postgre;

import static ua.com.foxminded.university.util.Validator.checkIfObjectNull;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.TeacherService;
import ua.com.foxminded.university.util.exceptions.TeacherNotFoundException;

@Service
@RequiredArgsConstructor
public class PostgreSqlTeacherService implements TeacherService {

    @NonNull
    private TeacherDao teacherDao;

    @Override
    public void addTeacher(Teacher teacher) {
        checkIfObjectNull(teacher);
        teacherDao.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.findAll();
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        checkIfObjectNull(teacher);
        teacherDao.delete(teacher);
    }

    @Override
    public Teacher findOne(long id) {
        return teacherDao.findById(id).orElseThrow(()-> new TeacherNotFoundException(id));
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        checkIfObjectNull(teacher);
        teacherDao.save(teacher);
    }
}
