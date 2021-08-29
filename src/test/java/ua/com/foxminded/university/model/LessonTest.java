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
        lesson.setGroupId(1L);
        lesson.setSubjectId(1L);
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
        assertEquals(1, lesson.getGroupId());
    }
    
    @Test
    public void setGroup_Id_shouldSetValueToGroupId() {
        lesson.setGroupId(2L);
        
        assertEquals(2, lesson.getGroupId());
    }
    
    @Test
    public void getSubjectId_shouldReturnSubjectId() {
        assertEquals(1, lesson.getSubjectId());
    }
    
    @Test
    public void setSubject_Id_shouldSetValueToSubjectId() {
        lesson.setSubjectId(2L);
        
        assertEquals(2, lesson.getSubjectId());
    }
}
