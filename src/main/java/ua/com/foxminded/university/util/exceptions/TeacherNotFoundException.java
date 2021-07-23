package ua.com.foxminded.university.util.exceptions;

@SuppressWarnings("serial")
public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException(Long id) {
        super("Could not find teacher with id " + id);
      }
}
