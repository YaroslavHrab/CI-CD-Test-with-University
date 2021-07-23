package ua.com.foxminded.university.util.exceptions;

@SuppressWarnings("serial")
public class GroupNotFoundException extends RuntimeException{

    public GroupNotFoundException(Long id) {
        super("Could not find group with id " + id);
      }
}
