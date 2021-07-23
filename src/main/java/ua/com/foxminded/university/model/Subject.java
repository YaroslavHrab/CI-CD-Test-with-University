package ua.com.foxminded.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "subjects")
public class Subject {

    @Column(name = "subject_name")
    @NotBlank(message = "Subject name can`t be empty")
    @Size(min = 2, max = 20, message = "Subject name length must be from 2 to 20 digits")
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Subject name must begin with A-Z and contain only letters")
    private String name;
    @Column(name = "subject_desc")
    private String description;
    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;
    @Column(name = "teacher_id", insertable = false, updatable = false)
    @Positive(message = "Teacher id must be positive integer")
    private Long teacher_id;

    public Subject() {
        
    }
    
    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s", name,
                teacher);
    }
}
