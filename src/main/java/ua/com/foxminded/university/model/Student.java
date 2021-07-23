package ua.com.foxminded.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    
    @Column(name = "first_name")
    @NotBlank(message = "Student name can`t be empty")
    @Size(min = 2, max = 20, message = "Student name length must be from 2 to 20 digits")
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Student name must begin with A-Z and contain only letters")
    private String name;
    @Column(name = "last_name")
    @NotBlank(message = "Student surname can`t be empty")
    @Size(min = 2, max = 20, message = "Student surname length must be from 2 to 20 digits")
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Student surname must begin with A-Z and contain only letters")
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    @Column(name = "group_id")
    @Positive(message = "Group id must be positive integer")
    private Long group_id;
    
    public Student() {
        
    }
    
    public Student(String name, String surname) {
       this.name = name;
       this.surname = surname;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }
}
