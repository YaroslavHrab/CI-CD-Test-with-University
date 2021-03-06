package ua.com.foxminded.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long>{

}
