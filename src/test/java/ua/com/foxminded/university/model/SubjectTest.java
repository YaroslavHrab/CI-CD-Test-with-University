package ua.com.foxminded.university.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubjectTest {

    Subject subject;

    @BeforeEach
    @Test
    public void generateSubject() {
        subject = new Subject("Math", "Test desc");
        subject.setTeacher(new Teacher("Ivan", "Ivanov"));
        subject.setId(1L);
        subject.setTeacher_id(1L);
    }

    @Test
    public void getName_shouldReturnSubjectName() {
        String expectedResult = "Math";

        assertEquals(expectedResult, subject.getName());
    }

    @Test
    public void getDescription_shouldReturnSubjectDescription() {
        String expectedResult = "Test desc";

        assertEquals(expectedResult, subject.getDescription());
    }

    @Test
    public void setTeacher_shouldSetTeacherToSubject()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Teacher teacher = new Teacher("Ivan", "Ivanov");

        subject.setTeacher(teacher);

        final Field field = subject.getClass().getDeclaredField("teacher");
        field.setAccessible(true);

        assertEquals(teacher, field.get(subject));
    }

    @Test
    public void getTeacher_shoulReturnSubjectTeacher() {
        Teacher teacher = new Teacher("Ivan", "Ivanov");

        subject.setTeacher(teacher);

        assertEquals(teacher, subject.getTeacher());
    }
    
    @Test
    public void getId_shouldReturnSubjectId() {
        assertEquals(1, subject.getId());
    }
    
    @Test
    public void setId_shouldSetValueToSubjectId() {
        subject.setId(2L);
        
        assertEquals(2, subject.getId());
    }
    
    @Test
    public void getTeacher_Id_shouldReturnTeacherId() {
        assertEquals(1, subject.getTeacher_id());
    }
    
    @Test
    public void getTeacher_Id_shouldSetValueToTeacherId() {
        subject.setTeacher_id(2L);
        
        assertEquals(2, subject.getTeacher_id());
    }
}
