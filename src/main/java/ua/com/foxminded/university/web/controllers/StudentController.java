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
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlStudentService;

@Controller
@RequestMapping("/cruds/students")
@RequiredArgsConstructor
public class StudentController {

	@NonNull
	private PostgreSqlStudentService studentService;
	@NonNull
	private PostgreSqlGroupService groupService;

	@GetMapping
	public ModelAndView getStudents() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", studentService.getAllStudents());
		mv.addObject("groups", groupService.getAllGroups());
		mv.setViewName("students/students");
		return mv;
	}

	@GetMapping("/newstudent")
	public ModelAndView newStudent() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", new Student());
		mv.addObject("groups", groupService.getAllGroups());
		mv.setViewName("students/add");
		return mv;
	}

	@PostMapping("/addstudent")
	public String addStudent(Model model, @ModelAttribute @Valid Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("groups", groupService.getAllGroups());
			return "students/add";
		}
		studentService.addStudent(student);
		return "redirect:/cruds/students";
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editStudent(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", studentService.findOne(id));
		mv.addObject("groups", groupService.getAllGroups());
		mv.setViewName("students/edit");
		return mv;
	}

	@PostMapping("/update")
	public String updateStudent(Model model, @ModelAttribute @Valid Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("groups", groupService.getAllGroups());
			return "students/edit";
		}
		studentService.updateStudent(student);
		return "redirect:/cruds/students";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") long id) {
		studentService.deleteStudent(studentService.findOne(id));
		return "redirect:/cruds/students";
	}
}
