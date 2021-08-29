package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Teacher;

@Transactional
@Repository
public interface LessonDao extends JpaRepository<Lesson, Long> {

	List<Lesson> findByGroup(Group group);
	
	@Query(value = "SELECT lesson FROM Lesson lesson WHERE lesson.groupId = :id AND CAST(lesson.beginingTime AS date)"
			+ " =  CAST(:date AS date)")
	List<Lesson> getLessonsForGroupOnDay(@Param("id") Long groupId, @Param("date") LocalDate date);

	List<Lesson> findBySubjectTeacher(Teacher teacher);

	@Query(value = "SELECT lesson FROM Lesson lesson INNER JOIN lesson.subject s INNER JOIN s.teacher t WHERE t.id = :id"
			+ " AND CAST(lesson.beginingTime AS date) =  CAST(:date AS date)")
	List<Lesson> getLessonsForTeacherOnDay(@Param("id") Long teacherId, @Param("date") LocalDate date);

}
