package ua.com.foxminded.university.service.postgre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.api.DBRider;

import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider
public class PostgreSqlStudentServiceIT {

	@Autowired
    private PostgreSqlStudentService studentService;

	@Autowired
	private DataSource dataSource;

    @SuppressWarnings("unused")
    private ConnectionHolder connectionHolder = () -> dataSource.getConnection();

    @Test
    @DataSet(value = {"groupDataset.yml","studentDataset.yml"}, cleanBefore = true, tableOrdering = {"groups", "students"})
    @ExpectedDataSet(value = "studentAddExpectedDataset.yml", ignoreCols = "student_id")
    void addStudent_shouldAddStudentToDatabase() {
        Student student = new Student("Test", "Test");
        student.setGroupId(1L);
        
        studentService.addStudent(student);
    }

    @Test
    @DataSet(value = {"groupDataset.yml","studentDataset.yml"}, cleanBefore = true, tableOrdering = {"groups", "students"})
    void getAllStudents_shouldReturnAllStudentsFromDatabase() {
        List<Student> students = studentService.getAllStudents();

        assertFalse(students.isEmpty());
        assertEquals(2, students.size());
    }

    @Test
    @DataSet(value = {"groupDataset.yml","studentDataset.yml"}, cleanBefore = true, tableOrdering = {"groups", "students"})
    @ExpectedDataSet(value = "studentDeleteExpectedDataset.yml")
    void deleteStudent_shouldDeleteStudentFromDatabase() {
        Student student = studentService.findOne(2);
        
        assertNotNull(student);
        studentService.deleteStudent(student);
    }

    @Test
    @DataSet(value = {"groupDataset.yml","studentDataset.yml"}, cleanBefore = true, tableOrdering = {"groups", "students"})
    void findOne_shouldReturnStudentAssociatedWithId() {
        Student student = studentService.findOne(1);
        
        assertTrue("Test1".equals(student.getName()) && "Test1".equals(student.getSurname()));
    }
    
    @Test
    @DataSet(value = {"groupDataset.yml","studentDataset.yml"}, cleanBefore = true, tableOrdering = {"groups", "students"})
    @ExpectedDataSet(value = "studentUpdateExpectedDataset.yml")
    void updateStudent_shouldUpdateStudentInDatabase() {
        Student student = studentService.findOne(1);
        assertNotNull(student);
        student.setName("Nottest");
        student.setSurname("Nottest");
        
        studentService.updateStudent(student);
    }    
}
