package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Teacher;

public interface LessonService {

    void addLesson(Lesson lesson);

    void deleteLesson(Lesson lesson);

    List<Lesson> getAllLessons();
    
    void updateLesson(Lesson lesson);
    
    Lesson findOne(long id);
    
    List<Lesson> getLessonsForGroupOnMonth(Group group);
    
    List<Lesson> getLessonsForGroupOnDay(Group group, LocalDate date);
    
    List<Lesson> getLessonsForTeacherOnMonth(Teacher teacher);
    
    List<Lesson> getLessonsForTeacherOnDay(Teacher teacher, LocalDate date);
}
