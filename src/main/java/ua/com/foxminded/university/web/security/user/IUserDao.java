package ua.com.foxminded.university.web.security.user;

public interface IUserDao {

    public User findOne(String username);
}
