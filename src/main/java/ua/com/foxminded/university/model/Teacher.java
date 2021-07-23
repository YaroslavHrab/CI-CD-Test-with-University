package ua.com.foxminded.university.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Column(name = "teacher_first_name")
    @NotBlank(message = "Teacher name can`t be empty")
    @Size(min = 2, max = 20, message = "Teacher name length must be from 2 to 20 digits")
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Teacher name must begin with A-Z and contain only letters")
    private String name;
    @Column(name = "teacher_last_name")
    @NotBlank(message = "Teacher surname can`t be empty")
    @Size(min = 2, max = 20, message = "Teacher surname length must be from 2 to 20 digits")
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Teacher surname must begin with A-Z and contain only letters")
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    
    public Teacher() {
        
    }
    
    public Teacher(String name, String surname) {
       this.name = name;
       this.surname = surname;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }
}
