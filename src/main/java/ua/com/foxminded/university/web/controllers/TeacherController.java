package ua.com.foxminded.university.web.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@Controller
@RequestMapping("/cruds/teachers")
@RequiredArgsConstructor
public class TeacherController {

	@NonNull
	private PostgreSqlTeacherService teacherService;

	@GetMapping
	public ModelAndView getTeachers() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("teachers", teacherService.getAllTeachers());
		mv.setViewName("teachers/teachers");
		return mv;
	}

	@GetMapping("/newteacher")
	public ModelAndView newTeacher() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("teacher", new Teacher());
		mv.setViewName("teachers/add");
		return mv;
	}

	@PostMapping("/addteacher")
	public String addTeacher(@ModelAttribute @Valid Teacher teacher, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "teachers/add";
		}
		teacherService.addTeacher(teacher);
		return "redirect:/cruds/teachers";
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editTeacher(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("teacher", teacherService.findOne(id));
		mv.setViewName("teachers/edit");
		return mv;
	}

	@PostMapping("/update")
	public String updateTeacher(@ModelAttribute @Valid Teacher teacher, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "teachers/edit";
		}
		teacherService.updateTeacher(teacher);
		return "redirect:/cruds/teachers";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteTeacher(@PathVariable(name = "id") long id) {
		teacherService.deleteTeacher(teacherService.findOne(id));
		return "redirect:/cruds/teachers";
	}
}
