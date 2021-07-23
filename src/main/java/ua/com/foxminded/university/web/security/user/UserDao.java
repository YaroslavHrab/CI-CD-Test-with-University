package ua.com.foxminded.university.web.security.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
public class UserDao implements IUserDao{

    @PersistenceContext
    EntityManager entityManager;

    public User findOne(String username) {
        return entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
