package ua.com.foxminded.university.web.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;

@Controller
@RequestMapping("/studentsTimetable")
@RequiredArgsConstructor
public class StudentsTimetableController {

	@NonNull
	private PostgreSqlGroupService groupService;
	@NonNull
	private PostgreSqlLessonService lessonService;

	@GetMapping
	public ModelAndView getTimetable() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("groups", groupService.getAllGroups());
		mv.setViewName("timetables/studentsChoiseForm");
		return mv;
	}

	@GetMapping("/onMonth")
	public ModelAndView onMonth(@RequestParam("groupId") long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("groups", groupService.getAllGroups());
		mv.addObject("lessons", lessonService.getLessonsForGroupOnMonth(groupService.findOne(id)));
		mv.setViewName("timetables/studentsTimetable");
		return mv;
	}

	@GetMapping("/onDay")
	public ModelAndView onDay(@RequestParam("groupId") long id, @RequestParam("beginningTime") String beginningTime) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("groups", groupService.getAllGroups());
		mv.addObject("lessons",
				lessonService.getLessonsForGroupOnDay(groupService.findOne(id), LocalDate.parse(beginningTime)));
		mv.setViewName("timetables/studentsTimetable");
		return mv;
	}
}
