package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public void removeUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void updateUser(User updateUser) {
        entityManager.merge(updateUser);
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user from User user", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id",
                User.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery
                ("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }
}




