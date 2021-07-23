package ua.com.foxminded.university.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {

    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;
    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;
    @Column(name = "lesson_number")
    @Range(min = 1, max = 5, message = "Lesson number must be in range from 1 to 5")
    private int lessonNumber;
    @Column(name = "begin_time")
    private LocalDateTime beginingTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;
    @Positive(message = "Group id must be positive integer")
    @Column(name = "group_id", insertable = false, updatable = false)
    private Long group_id;
    @Positive(message = "Subject id must be positive integer")
    @Column(name = "subject_id", insertable = false, updatable = false)
    private Long subject_id;

    public Lesson() {
        
    }
    
    public Lesson(int lessonNumber, LocalDateTime beginingTime) {
        this.lessonNumber = lessonNumber;
        this.beginingTime = beginingTime;
    }
    
    public void setBeginingTime(String beginingTime) {
    	this.beginingTime = LocalDateTime.parse(beginingTime);
    }
    
    public void setBeginingTime(LocalDateTime beginingTime) {
    	this.beginingTime = beginingTime;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", beginingTime,
                subject, group);
    }
}
