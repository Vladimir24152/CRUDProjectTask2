package org.example.dao;

import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createTableUsers() {
        sessionFactory.getCurrentSession().createNativeQuery(RequestConstants.CREATE_USERS_TABLE).executeUpdate();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User newUser) {
        sessionFactory.getCurrentSession().update(newUser);
    }

    @Override
    public void deleteUser(long id) {
        sessionFactory.getCurrentSession().delete(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery(RequestConstants.GET_ALL_USERS_HQL,User.class).list();
    }
}