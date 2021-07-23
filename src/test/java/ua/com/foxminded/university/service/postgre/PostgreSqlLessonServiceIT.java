package ua.com.foxminded.university.service.postgre;

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
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider
public class PostgreSqlLessonServiceIT {

	@Autowired
    private PostgreSqlLessonService lessonService;
	@Autowired
    private PostgreSqlSubjectService subjectService;
	@Autowired
    private PostgreSqlGroupService groupService;
	@Autowired
    private PostgreSqlTeacherService teacherService;

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
        lesson.setGroup(groupService.findOne(2L));
        lesson.setSubject(subjectService.findOne(2));
        
        lessonService.addLesson(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getAllLessons_shouldReturnAllLessonsFromDatabase() {
        List<Lesson> lessons = lessonService.getAllLessons();

        assertFalse(lessons.isEmpty());
        assertEquals(2, lessons.size());
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    @ExpectedDataSet(value = "lessonDeleteExpectedDataset.yml")
    void deleteLesson_shouldDeleteLessonFromDatabase() {
        Lesson lesson = lessonService.findOne(2);
        
        assertNotNull(lesson);
        lessonService.deleteLesson(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void findOne_shouldReturnLessonAssociatedWithId() {
        Lesson lesson = lessonService.findOne(1);
        
        assertEquals("2021-03-09T08:30 | Test1 | Test1 Test1 | Test1", lesson.toString());
    }
    
    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    @ExpectedDataSet(value = "lessonUpdateExpectedDataset.yml")
    void updateLesson_shouldUpdateLessonInDatabase() {
        Lesson lesson = lessonService.findOne(2);
        assertNotNull(lesson);
        lesson.setGroup(groupService.findOne(1L));
        lesson.setSubject(subjectService.findOne(1));
        
        lessonService.updateLesson(lesson);
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForGroupOnMonth_shouldReturnListOfLessonsForGroupOnMonth() {
        Group group = groupService.findOne(1L);
        List<Lesson> lessons = lessonService.getLessonsForGroupOnMonth(group);
        
        assertEquals(
                "2021-03-09T08:30 | Test1 | Test1 Test1 | Test1",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForGroupOnDay_shouldReturnListOfLessonsForGroupOnDay() {
        Group group = groupService.findOne(1L);
        List<Lesson> lessons = lessonService.getLessonsForGroupOnDay(group, LocalDate.parse("2021-03-09"));
        
        assertEquals(
                "2021-03-09T08:30 | Test1 | Test1 Test1 | Test1",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }

    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForTeacherOnMonth_shouldReturnListOfLessonsForTeacherOnMonth() {
        Teacher teacher = teacherService.findOne(2);
        List<Lesson> lessons = lessonService.getLessonsForTeacherOnMonth(teacher);
        
        assertEquals(
                "2021-03-09T12:20 | Test2 | Test2 Test2 | Test2",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }
    
    @Test
    @DataSet(value = { "teacherDataset.yml", "subjectDataset.yml", "groupDataset.yml",
            "lessonDataset.yml" }, cleanBefore = true, tableOrdering = { "teachers", "subjects", "groups", "lessons" })
    void getLessonsForTeacherOnDay_shouldReturnListOfLessonsForTeacherOnDay() {
        Teacher teacher = teacherService.findOne(2);
        List<Lesson> lessons = lessonService.getLessonsForTeacherOnDay(teacher, LocalDate.parse("2021-03-09"));
        
        assertEquals(
                "2021-03-09T12:20 | Test2 | Test2 Test2 | Test2",
                lessons.stream().map(l -> l.toString()).collect(Collectors.joining("\n")));
    }
}
