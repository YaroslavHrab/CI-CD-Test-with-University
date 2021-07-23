package ua.com.foxminded.university.dao;

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
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.api.DBRider;

import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider 
public class TeacherDaoIT{
	
	@Autowired
    private TeacherDao teacherDao;
    
	@Autowired
	private DataSource dataSource;

    @SuppressWarnings("unused")
    private ConnectionHolder connectionHolder = () -> dataSource.getConnection();

    @Test
    @DataSet(value = "teacherDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "teacherAddExpectedDataset.yml", ignoreCols = "teacher_id")
    void addTeacher_shouldAddTeacherToDatabase() {
        Teacher teacher = new Teacher("Test", "Test");
        
        teacherDao.save(teacher);
    }

    @Test
    @DataSet(value = "teacherDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void getAllTeachers_shouldReturnAllTeachersFromDatabase() {
        List<Teacher> teachers = teacherDao.findAll();
        
        assertFalse(teachers.isEmpty());
        assertEquals(2, teachers.size());

    }

    @Test
    @DataSet(value = "teacherDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "teacherDeleteExpectedDataset.yml")
    void deleteTeacher_shouldDeleteTeacherFromDatabase() {
        Teacher teacher = teacherDao.findById(2L).get();
        
        assertNotNull(teacher);
        teacherDao.delete(teacher);
    }
    
    @Test
    @DataSet(value = "teacherDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void findOne_shouldReturnTeacherAssociatedWithId() {
        Teacher teacher = teacherDao.findById(1L).get();
        
        assertTrue("Test1".equals(teacher.getName()) && "Test1".equals(teacher.getSurname()));
    }
    
    @Test
    @DataSet(value = "teacherDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "teacherUpdateExpectedDataset.yml")
    void updateTeacher_shouldUpdateTeacherInDatabase() {
        Teacher teacher = teacherDao.findById(1L).get();
        assertNotNull(teacher);
        teacher.setName("Nottest");
        teacher.setSurname("Nottest");
        
        teacherDao.save(teacher);
    }
}
