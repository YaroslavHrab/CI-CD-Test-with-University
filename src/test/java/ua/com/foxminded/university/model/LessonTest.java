package ua.com.foxminded.university.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LessonTest {

    Lesson lesson;
    Group group;
    Subject subject;

    @BeforeEach
    @Test
    public void generateLesson() {
        group = new Group("IP-04");
        subject = new Subject("Math", "Test desc");
        subject.setTeacher(new Teacher("Ivan", "Ivanov"));
        lesson = new Lesson(1,
                LocalDateTime.of(2021, 2, 4, 8, 30));
        lesson.setGroup(group);
        lesson.setSubject(subject);
        lesson.setId(1L);
        lesson.setGroup_id(1L);
        lesson.setSubject_id(1L);
    }
    
    @Test
    public void getGroup_shouldReturnGroupAssingedToLesson() {
        assertEquals(this.group, lesson.getGroup());
    }
    
    @Test
    public void getSubject_shouldReturnSubjectAssingedToLesson() {
        assertEquals(this.subject, lesson.getSubject());
    }
    
    @Test
    public void getBeginingTime_shouldReturnLessonBeginingTime() {
        assertEquals(LocalDateTime.of(2021, 2, 4, 8, 30), lesson.getBeginingTime());
    }
    
    @Test
    public void getLessonNumber_shouldReturnLessonNumber() {
        int expectedNumber = 1;
        
        assertEquals(expectedNumber, lesson.getLessonNumber());
    }
    
    @Test
    public void toString_shouldReturnStringPresentationOfGroup() {
        assertEquals("2021-02-04T08:30 | Math | Ivan Ivanov | IP-04", lesson.toString());
    }
    
    @Test
    public void getId_shouldReturnLessonId() {
        assertEquals(1, lesson.getId());
    }
    
    @Test
    public void setId_shouldSetValueToLessonId() {
        lesson.setId(2L);
        
        assertEquals(2, lesson.getId());
    }
    
    @Test
    public void getGroupId_shouldReturnGroupId() {
        assertEquals(1, lesson.getGroup_id());
    }
    
    @Test
    public void setGroup_Id_shouldSetValueToGroupId() {
        lesson.setGroup_id(2L);
        
        assertEquals(2, lesson.getGroup_id());
    }
    
    @Test
    public void getSubjectId_shouldReturnSubjectId() {
        assertEquals(1, lesson.getSubject_id());
    }
    
    @Test
    public void setSubject_Id_shouldSetValueToSubjectId() {
        lesson.setSubject_id(2L);
        
        assertEquals(2, lesson.getSubject_id());
    }
}
