package ua.com.foxminded.university.web.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.com.foxminded.university.util.exceptions.GroupNotFoundException;
import ua.com.foxminded.university.util.exceptions.LessonNotFoundException;
import ua.com.foxminded.university.util.exceptions.StudentNotFoundException;
import ua.com.foxminded.university.util.exceptions.SubjectNotFoundException;
import ua.com.foxminded.university.util.exceptions.TeacherNotFoundException;

@ResponseBody
@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundAdvice {

    @ExceptionHandler(GroupNotFoundException.class)
    String groupNotFoundHandler(GroupNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    String studentNotFoundHandler(StudentNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    String teacherNotFoundHandler(TeacherNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    String subjectNotFoundHandler(SubjectNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(LessonNotFoundException.class)
    String lessonNotFoundHandler(LessonNotFoundException ex) {
        return ex.getMessage();
    }

}