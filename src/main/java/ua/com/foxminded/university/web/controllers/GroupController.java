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
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;

@Controller
@RequestMapping("/cruds/groups")
@RequiredArgsConstructor
public class GroupController {

	@NonNull
	private PostgreSqlGroupService groupService;

	@GetMapping
	public ModelAndView getGroups() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("groups", groupService.getAllGroups());
		mv.setViewName("groups/groups");
		return mv;
	}

	@GetMapping("/newgroup")
	public ModelAndView newGroup() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("group", new Group());
		mv.setViewName("groups/add");
		return mv;
	}

	@PostMapping("/addgroup")
	public String addGroup(@ModelAttribute @Valid Group group, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "groups/add";
		}
		groupService.addGroup(group);
		return "redirect:/cruds/groups";
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editGroup(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("group", groupService.findOne(id));
		mv.setViewName("groups/edit");
		return mv;
	}

	@PostMapping("/update")
	public String updateGroup(@ModelAttribute @Valid Group group, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "groups/edit";
		}
		groupService.updateGroup(group);
		return "redirect:/cruds/groups";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteGroup(@PathVariable(name = "id") long id) {
		groupService.deleteGroup(groupService.findOne(id));
		return "redirect:/cruds/groups";
	}
}
