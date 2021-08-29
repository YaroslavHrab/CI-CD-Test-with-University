package ua.com.foxminded.university.web.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;

@Controller
@RequestMapping("/cruds/lessons")
@RequiredArgsConstructor
public class LessonController {

    @NonNull
    private PostgreSqlLessonService lessonService;
    @NonNull
    private PostgreSqlSubjectService subjectService;
    @NonNull
    private PostgreSqlGroupService groupService;

    @GetMapping
    public ModelAndView getLessons() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lessons", lessonService.getAllLessons());
        mv.addObject("subjects", subjectService.getAllSubjects());
        mv.addObject("groups", groupService.getAllGroups());
        mv.setViewName("lessons/lessons");
        return mv;
    }

    @GetMapping("/newlesson")
    public ModelAndView newLessons() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lesson", new Lesson());
        mv.addObject("subjects", subjectService.getAllSubjects());
        mv.addObject("groups", groupService.getAllGroups());
        mv.setViewName("lessons/add");
        return mv;
    }

    @PostMapping("/addlesson")
    public String addLesson(Model model, @ModelAttribute @Valid Lesson lesson, BindingResult bindingResult,
            @RequestParam("beginningTime") String beginningTime) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("groups", groupService.getAllGroups());
            return "subjects/add";
        }
        lesson.setBeginingTime(LocalDateTime.parse(beginningTime));
        lesson.setSubject(subjectService.findOne(lesson.getSubjectId()));
        lesson.setGroup(groupService.findOne(lesson.getGroupId()));
        lessonService.addLesson(lesson);
        return "redirect:/cruds/lessons";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editLesson(@PathVariable(name = "id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lesson", lessonService.findOne(id));
        mv.addObject("subjects", subjectService.getAllSubjects());
        mv.addObject("groups", groupService.getAllGroups());
        mv.setViewName("lessons/edit");
        return mv;
    }

    @PostMapping("/update")
    public String updateLesson(Model model, @ModelAttribute @Valid Lesson lesson, BindingResult bindingResult,
            @RequestParam("beginningTime") String beginningTime) {  
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("groups", groupService.getAllGroups());
            return "subjects/add";
        }
        lesson.setBeginingTime(LocalDateTime.parse(beginningTime));
        lesson.setGroup(groupService.findOne(lesson.getGroupId()));
        lesson.setSubject(subjectService.findOne(lesson.getSubjectId()));
        lessonService.updateLesson(lesson);
        return "redirect:/cruds/lessons";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteLesson(@PathVariable(name = "id") long id) {
        lessonService.deleteLesson(lessonService.findOne(id));
        return "redirect:/cruds/lessons";
    }
}
