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
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@RestController
@RequestMapping("/m.cruds")
@RequiredArgsConstructor
public class TeacherControllerRest {

    @NonNull
    private PostgreSqlTeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/teachers")
    public void newTeacher(@RequestBody @Valid Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @GetMapping("/teachers/{id}")
    public Teacher findOne(@PathVariable Long id) {
      return teacherService.findOne(id);
    }

    @PutMapping("/teachers/{id}")
    public void updateGroup(@RequestBody @Valid Teacher newTeacher, @PathVariable Long id) {
        Teacher teacher = teacherService.findOne(id);
        teacher.setName(newTeacher.getName());
        teacher.setSurname(newTeacher.getSurname());
        teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    void deleteEmployee(@PathVariable Long id) {
       teacherService.deleteTeacher(teacherService.findOne(id));
    }
}
