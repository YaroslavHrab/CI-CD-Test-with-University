package ua.com.foxminded.university.web.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@Controller
@RequestMapping("/teachersTimetable")
@RequiredArgsConstructor
public class TeachersTimetableController {

    @NonNull
    private PostgreSqlTeacherService teacherService;
    @NonNull
    private PostgreSqlLessonService lessonService;
    
    @GetMapping
    public ModelAndView getTimetable() {
        ModelAndView  mv = new ModelAndView();
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.setViewName("timetables/teachersChoiseForm");
        return mv;
    }
    
    @GetMapping("/onMonth")
    public ModelAndView onMonth(@RequestParam("teacherId") long id) {
        ModelAndView  mv = new ModelAndView();
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.addObject("lessons", lessonService.getLessonsForTeacherOnMonth(teacherService.findOne(id)));
        mv.setViewName("timetables/teachersTimetable");
        return mv;
    }
    
    @GetMapping("/onDay")
    public ModelAndView onDay(@RequestParam("teacherId") long id, @RequestParam("beginningTime") String beginningTime) {
        ModelAndView  mv = new ModelAndView();
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.addObject("lessons", lessonService.getLessonsForTeacherOnDay(teacherService.findOne(id), LocalDate.parse(beginningTime)));
        mv.setViewName("timetables/teachersTimetable");
        return mv;
    }
}
