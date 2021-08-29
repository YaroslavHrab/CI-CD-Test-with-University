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
import ua.com.foxminded.university.model.Subject;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;

@RestController
@RequestMapping("/m.cruds")
@RequiredArgsConstructor
public class SubjectControllerRest {

    @NonNull
    private PostgreSqlSubjectService subjectService;

    @GetMapping("/subjects")
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/subjects")
    public void newSubject(@RequestBody @Valid Subject subject) {
        subjectService.addSubject(subject);
    }
    
    @GetMapping("/subjects/{id}")
    public Subject findOne(@PathVariable Long id) {
      return subjectService.findOne(id);
    }
    
    @PutMapping("/subjects/{id}")
    public void updateSubject(@RequestBody @Valid Subject newSubject, @PathVariable Long id) {
        Subject subject = subjectService.findOne(id);
        subject.setName(newSubject.getName());
        subject.setTeacher(newSubject.getTeacher());
        subject.setTeacherId(newSubject.getTeacherId());
        subject.setDescription(newSubject.getDescription());
        subjectService.updateSubject(subject);
    }
    
    @DeleteMapping("/subjects/{id}")
    void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(subjectService.findOne(id));
    }
}
