package ua.com.foxminded.university.web.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.model.Subject;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@Controller
@RequestMapping("/cruds/subjects")
@RequiredArgsConstructor
public class SubjectController {

    @NonNull
    private PostgreSqlSubjectService subjectService;
    @NonNull
    private PostgreSqlTeacherService teacherService;

    @GetMapping
    public ModelAndView getSubjects() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("subjects", subjectService.getAllSubjects());
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.setViewName("subjects/subjects");
        return mv;
    }
    
    @GetMapping("/newsubject")
    public ModelAndView newSubject() {
    	ModelAndView mv = new ModelAndView();
        mv.addObject("subject", new Subject());
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.setViewName("subjects/add");
        return mv;
    }
    
    @PostMapping("/addsubject")
    public String addSubject(Model model, @ModelAttribute @Valid Subject subject, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
			model.addAttribute("teachers", teacherService.getAllTeachers());
			return "subjects/add";
		}
        subject.setTeacher(teacherService.findOne(subject.getTeacherId()));
        subjectService.addSubject(subject);
        return "redirect:/cruds/subjects";
    }
    
    @GetMapping(value = "/edit/{id}")
    public ModelAndView editSubject(@PathVariable(name = "id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("subject", subjectService.findOne(id));
        mv.addObject("teachers", teacherService.getAllTeachers());
        mv.setViewName("subjects/edit");
        return mv;
    }
    
    @PostMapping("/update")
    public String updateSubject(Model model, @ModelAttribute @Valid Subject subject, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
			model.addAttribute("teachers", teacherService.getAllTeachers());
			return "subjects/edit";
		}
        subject.setTeacher(teacherService.findOne(subject.getTeacherId()));
        subjectService.updateSubject(subject);
        return "redirect:/cruds/subjects";
    }
    
    @GetMapping(value = "/delete/{id}")
    public String deleteSubject(@PathVariable(name = "id") long id) {
        subjectService.deleteSubject(subjectService.findOne(id));
        return "redirect:/cruds/subjects";
    }
}
