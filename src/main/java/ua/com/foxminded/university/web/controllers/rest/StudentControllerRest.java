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
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.postgre.PostgreSqlStudentService;

@RestController
@RequestMapping("/m.cruds")
@RequiredArgsConstructor
public class StudentControllerRest {

    @NonNull
    private PostgreSqlStudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public void newStudent(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
    }
    
    @GetMapping("/students/{id}")
    public Student findOne(@PathVariable Long id) {
      return studentService.findOne(id);
    }
    
    @PutMapping("/students/{id}")
    public void updateStudent(@RequestBody @Valid Student newStudent, @PathVariable Long id) {
        Student student = studentService.findOne(id);
        student.setName(newStudent.getName());
        student.setSurname(newStudent.getSurname());
        student.setGroup_id(newStudent.getGroup_id());
        studentService.updateStudent(student);
    }
    
    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(studentService.findOne(id));
    }
}
