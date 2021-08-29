package ua.com.foxminded.university.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

    Student student;

    @Test
    @BeforeEach
    public void generateStudent() {
        student = new Student("Ivan", "Ivanov");
        student.setId(1L);
        student.setGroupId(1L);
    }

    @Test
    public void getName_shouldReturnStudentsName() {
        String expectedResult = "Ivan";

        assertEquals(expectedResult, student.getName());
    }

    @Test
    public void getSurname_shouldReturnStudentsSurname() {
        String expectedResult = "Ivanov";

        assertEquals(expectedResult, student.getSurname());
    }
    
    @Test
    public void getId_shouldReturnStudentId() {
        assertEquals(1, student.getId());
    }
    
    @Test
    public void setId_shouldSetValueToStudentId() {
        student.setId(2L);
        
        assertEquals(2, student.getId());
    }
    
    @Test
    public void getGroup_Id_shouldReturnGroupId() {
        assertEquals(1, student.getGroupId());
    }
    
    @Test
    public void setId_shouldSetValueToGroupId() {
        student.setGroupId(2L);
        
        assertEquals(2, student.getGroupId());
    }
}
