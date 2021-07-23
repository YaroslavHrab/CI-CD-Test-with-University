package ua.com.foxminded.university.util.exceptions;

@SuppressWarnings("serial")
public class LessonNotFoundException extends RuntimeException{

    public LessonNotFoundException(Long id) {
        super("Could not find teacher with id " + id);
      }
}
