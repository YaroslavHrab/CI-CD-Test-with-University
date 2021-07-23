package ua.com.foxminded.university.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeacherTest {
    
    Teacher teacher;
    
    @BeforeEach
    @Test
    public void generateTeacher() {
        teacher = new Teacher("Ivan", "Ivanov");
        teacher.setId(1L);
    }
    
    @Test
    public void getName_shouldReturnTeachersName() {
        String expectedResult = "Ivan";

        assertEquals(expectedResult, teacher.getName());
    }
    
    @Test
    public void getSurname_shouldReturnTeachersSurname() {
        String expectedResult = "Ivanov";

        assertEquals(expectedResult, teacher.getSurname());
    }
    
    @Test
    public void getId_shouldReturnTeacherId() {
        assertEquals(1, teacher.getId());
    }
    
    @Test
    public void setId_shouldSetValueToTeacherId() {
        teacher.setId(2L);
        
        assertEquals(2, teacher.getId());
    }

    @Test
    public void toString_shouldReturnStringPresentationOfGroup() {
        assertEquals("Ivan Ivanov", teacher.toString());
    }
}
