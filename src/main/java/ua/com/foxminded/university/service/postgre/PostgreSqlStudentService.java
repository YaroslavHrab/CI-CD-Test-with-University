package ua.com.foxminded.university.service.postgre;

import static ua.com.foxminded.university.util.Validator.checkIfObjectNull;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.StudentService;
import ua.com.foxminded.university.util.exceptions.StudentNotFoundException;

@Service
@RequiredArgsConstructor
public class PostgreSqlStudentService implements StudentService {

    @NonNull
    private StudentDao studentDao;

    @Override
    public void addStudent(Student student) {
        checkIfObjectNull(student);
        studentDao.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void updateStudent(Student student) {
        checkIfObjectNull(student);
        studentDao.save(student);
    }
    
    @Override
    public void deleteStudent(Student student) {
        checkIfObjectNull(student);
        studentDao.delete(student);
    }

    @Override
    public Student findOne(long id) {
        return studentDao.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
    }
}
