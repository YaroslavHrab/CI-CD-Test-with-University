package ua.com.foxminded.university.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.SubjectDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider
public class LessonDaoIT {
	
	@Autowired
    private LessonDao lessonDao;
	@Autowired
    private SubjectDao subjectDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private TeacherDao teacherDao;

	@Autowired
	private DataSource dataSource;

    @SuppressWarnings("unused")
    private ConnectionHolder connectionHolder = () -> dataSource.getConnection();

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    @ExpectedDataSet(value = "lessonAddExpectedDataset.yml", ignoreCols = "lesson_id")
    void addLesson_shouldAddLessonToDatabase() {
        Lesson lesson = new Lesson(4, LocalDateTime.parse("2021-03-09T14:15"));
        lesson.setGroup(groupDao.findById(2L).get());
        lesson.setSubject(subjectDao.findById(2L).get());
        
        lessonDao.save(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getAllLessons_shouldReturnAllLessonsFromDatabase() {
        List<Lesson> lessons = lessonDao.findAll();

        assertFalse(lessons.isEmpty());
        assertEquals(2, lessons.size());
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    @ExpectedDataSet(value = "lessonDeleteExpectedDataset.yml")
    void deleteLesson_shouldDeleteLessonFromDatabase() {
        Lesson lesson = lessonDao.findById(2L).get();
        
        assertNotNull(lesson);
        lessonDao.delete(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void findOne_shouldReturnLessonAssociatedWithId() {
        Lesson lesson = lessonDao.findById(1L).get();
        
        assertEquals("2021-03-09T08:30 | Test1 | Test1 Test1 | Test1", lesson.toString());
    }
    
    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    @ExpectedDataSet(value = "lessonUpdateExpectedDataset.yml")
    void updateLesson_shouldUpdateLessonInDatabase() {
        Lesson lesson = lessonDao.findById(2L).get();
        assertNotNull(lesson);
        lesson.setGroup(groupDao.findById(1L).get());
        lesson.setSubject(subjectDao.findById(1L).get());
        
        lessonDao.save(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForGroupOnMonth_shouldReturnListOfLessonsForGroupOnMonth() {
        Group group = groupDao.findById(1L).get();
        List<Lesson> lessons = lessonDao.findByGroup(group);
        
        assertEquals(
                "2021-03-09T08:30 | Test1 | Test1 Test1 | Test1",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForGroupOnDay_shouldReturnListOfLessonsForGroupOnDay() {
        Group group = groupDao.findById(1L).get();
        List<Lesson> lessons = lessonDao.getLessonsForGroupOnDay(group.getId(), LocalDate.of(2021, 3, 9));
        
        assertEquals(
                "2021-03-09T08:30 | Test1 | Test1 Test1 | Test1",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForTeacherOnMonth_shouldReturnListOfLessonsForTeacherOnMonth() {
        Teacher teacher = teacherDao.findById(2L).get();
        List<Lesson> lessons = lessonDao.findBySubjectTeacher(teacher);
        
        assertEquals(
                "2021-03-09T12:20 | Test2 | Test2 Test2 | Test2",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }
    
    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForTeacherOnDay_shouldReturnListOfLessonsForTeacherOnDay() {
        Teacher teacher = teacherDao.findById(2L).get();
        List<Lesson> lessons = lessonDao.getLessonsForTeacherOnDay(teacher.getId(), LocalDate.of(2021, 3, 9));
        assertEquals(
                "2021-03-09T12:20 | Test2 | Test2 Test2 | Test2",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }
}
