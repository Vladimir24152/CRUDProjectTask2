package org.example.dao;

import org.example.entity.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTableUsers() {
        entityManager.createNativeQuery(RequestConstants.CREATE_USERS_TABLE).executeUpdate();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User newUser) {
        entityManager.merge(newUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery(RequestConstants.GET_ALL_USERS_HQL,User.class).getResultList();
    }
}