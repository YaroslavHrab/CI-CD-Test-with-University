package ua.com.foxminded.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Teacher;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {

}
