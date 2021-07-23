package ua.com.foxminded.university.web.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;

@RestController
@RequestMapping("/m.cruds")
@RequiredArgsConstructor
public class LessonControllerRest {

    @NonNull
    private PostgreSqlLessonService lessonService;

    @GetMapping("/lessons")
    public List<Lesson> getLessons() {
        return lessonService.getAllLessons();
    }

    @PostMapping("/lessons")
    public void newLessons(@RequestBody @Valid Lesson lesson) {
        lessonService.addLesson(lesson);
    }
    
    @GetMapping("/lessons/{id}")
    public Lesson findOne(@PathVariable Long id) {
      return lessonService.findOne(id);
    }
    
    @PutMapping("/lessons/{id}")
    public void updateLesson(@RequestBody @Valid Lesson newLesson, @PathVariable Long id) {
        Lesson lesson = lessonService.findOne(id);
        lesson.setBeginingTime(newLesson.getBeginingTime());
        lesson.setGroup(newLesson.getGroup());
        lesson.setGroup_id(newLesson.getGroup_id());
        lesson.setLessonNumber(newLesson.getLessonNumber());
        lesson.setSubject(newLesson.getSubject());
        lesson.setSubject_id(newLesson.getGroup_id());
        lessonService.updateLesson(lesson);
    }
    
    @DeleteMapping("/lessons/{id}")
    void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(lessonService.findOne(id));
    }
}
