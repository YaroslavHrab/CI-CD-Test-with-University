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
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.api.DBRider;

import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.dao.SubjectDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider
public class SubjectDaoIT {

	@Autowired
    private SubjectDao subjectDao;
	
    @Autowired
	private TeacherDao teacherDao;

    @Autowired
	private DataSource dataSource;

    @SuppressWarnings("unused")
    private ConnectionHolder connectionHolder = () -> dataSource.getConnection();
    
    @Test
    @DataSet(value = {"teacherDataset.yml","subjectDataset.yml"}, cleanBefore = true, tableOrdering = {"teachers","subjects"})
    @ExpectedDataSet(value = "subjectAddExpectedDataset.yml", ignoreCols = "subject_id")
    void addSubject_shouldAddSubjectToDatabase() {
        Subject subject = new Subject("Test", "Desc");
        subject.setTeacher(teacherDao.findById(1L).get());
        
        subjectDao.save(subject);
    }

    @Test
    @DataSet(value = {"teacherDataset.yml","subjectDataset.yml"}, cleanBefore = true, tableOrdering = {"teachers","subjects"})
    void getAllSubjects_shouldReturnAllSubjectsFromDatabase() {
        List<Subject> subjects = subjectDao.findAll();
        
        assertFalse(subjects.isEmpty());
        assertEquals(2, subjects.size());
    }

    @Test
    @DataSet(value = {"teacherDataset.yml","subjectDataset.yml"}, cleanBefore = true, tableOrdering = {"teachers","subjects"})
    @ExpectedDataSet(value = "subjectDeleteExpectedDataset.yml")
    void deleteSubject_shouldDeleteSubjectFromDatabase() {
        Subject subject = subjectDao.findById(2L).get();
        
        assertNotNull(subject);
        subjectDao.delete(subject);
    }
    
    @Test
    @DataSet(value = {"teacherDataset.yml","subjectDataset.yml"}, cleanBefore = true, tableOrdering = {"teachers","subjects"})
    void findOne_shouldReturnSubjectAssociatedWithId() {
        Subject subject = subjectDao.findById(1L).get();

        assertTrue("Test1".equals(subject.getName()) && "Desc".equals(subject.getDescription()) && subject.getTeacher().getId() == 1);
    }

    @Test
    @DataSet(value = {"teacherDataset.yml","subjectDataset.yml"}, cleanBefore = true, tableOrdering = {"teachers","subjects"})
    @ExpectedDataSet(value = "subjectUpdateExpectedDataset.yml")
    void updateSubject_shouldUpdateSubjectInDatabase() {
        Subject subject = subjectDao.findById(1L).get();
        assertNotNull(subject);
        subject.setName("Nottest");
        
        subjectDao.save(subject);
    }
}
