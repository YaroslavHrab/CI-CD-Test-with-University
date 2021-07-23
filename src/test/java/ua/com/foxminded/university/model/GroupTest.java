package ua.com.foxminded.university.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroupTest {
    
    Group group;
    
    @BeforeEach
    @Test
    public void generateGroup() {
        group = new Group("IP-04");
        group.setId(1L);
    }
    
    @Test
    public void getName_shouldReturnGroupsName() {
        String expectedResult = "IP-04";

        assertEquals(expectedResult, group.getName());
    }
    
    @Test
    public void getStudents_shouldReturnListOfStudents() {
        List<String> expectedResult = new ArrayList<>();
        
        assertEquals(expectedResult, group.getStudents());
    }
    
    @Test
    public void addStudentToGroup_shoudAddNewStudentToStudentsList() {
        int expectedSize = 1;
        
        group.addStudentToGroup(new Student("Ivan", "Ivanov"));
        
        assertEquals(expectedSize, group.getStudents().size());
    }
    
    @Test
    public void getId_shouldReturnGroupId() {
        assertEquals(1, group.getId());
    }
    
    @Test
    public void setId_shouldSetValueToGroupId() {
        group.setId(2L);
        
        assertEquals(2, group.getId());
    }
    
    @Test
    public void toString_shouldReturnStringPresentationOfGroup() {
        assertEquals("IP-04", group.toString());
    }
}


