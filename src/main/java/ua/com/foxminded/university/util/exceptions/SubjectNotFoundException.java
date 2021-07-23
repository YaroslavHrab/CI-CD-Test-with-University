package ua.com.foxminded.university.util.exceptions;

@SuppressWarnings("serial")
public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException(Long id) {
        super("Could not find subject with id " + id);
      }
}
