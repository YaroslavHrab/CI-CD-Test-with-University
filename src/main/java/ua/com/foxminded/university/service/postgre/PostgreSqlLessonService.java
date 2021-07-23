package ua.com.foxminded.university.service.postgre;

import static ua.com.foxminded.university.util.Validator.checkIfObjectNull;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.LessonService;
import ua.com.foxminded.university.util.exceptions.LessonNotFoundException;

@Service
@RequiredArgsConstructor
public class PostgreSqlLessonService implements LessonService {

    @NonNull
    private GroupDao groupDao;
    @NonNull
    private LessonDao lessonDao;

    @Override
    public void addLesson(Lesson lesson) {
        checkIfObjectNull(lesson);
        lessonDao.save(lesson);
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        checkIfObjectNull(lesson);
        lessonDao.delete(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDao.findAll();
    }

    @Override
    public Lesson findOne(long id) {
        return lessonDao.findById(id).orElseThrow(()->new LessonNotFoundException(id));
    }

    @Override
    public List<Lesson> getLessonsForGroupOnMonth(Group group) {
        checkIfObjectNull(group);
        return lessonDao.findByGroup(group);
    }

    @Override
    public List<Lesson> getLessonsForGroupOnDay(Group group, LocalDate date) {
        checkIfObjectNull(group);
        checkIfObjectNull(date);
        return lessonDao.getLessonsForGroupOnDay(group.getId(), date);
    }

    @Override
    public List<Lesson> getLessonsForTeacherOnMonth(Teacher teacher) {
        checkIfObjectNull(teacher);
        return lessonDao.findBySubjectTeacher(teacher);
    }

    @Override
    public List<Lesson> getLessonsForTeacherOnDay(Teacher teacher, LocalDate date) {
        checkIfObjectNull(teacher);
        checkIfObjectNull(date);
        return lessonDao.getLessonsForTeacherOnDay(teacher.getId(), date);
    }

    @Override
    public void updateLesson(Lesson lesson) {
        checkIfObjectNull(lesson);
        lessonDao.save(lesson);
    }
}
