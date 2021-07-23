package ua.com.foxminded.university.util.exceptions;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id) {
        super("Could not find student with id " + id);
      }
}
