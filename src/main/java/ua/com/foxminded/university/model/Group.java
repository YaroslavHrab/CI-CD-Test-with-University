package ua.com.foxminded.university.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Column(name = "group_name")
    @NotBlank(message = "Group name can`t be empty")
    @Size(min = 5, max = 5, message = "Group name length must be 5 digits")
    @Pattern(regexp = "[A-Z]{2}-\\d{2}", message = "Group name must be \"AA-00\" format")
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private List<Student> students;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    public Group() {
        
    }
    
    public Group(String name) {
        this.students = new ArrayList<>();
        this.name = name;
    }
    
    public void addStudentToGroup(Student student) {
        this.students.add(student);
    }
    
    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
