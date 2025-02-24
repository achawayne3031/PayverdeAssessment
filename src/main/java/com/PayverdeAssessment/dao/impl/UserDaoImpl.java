package com.PayverdeAssessment.dao.impl;

import com.PayverdeAssessment.dao.UserDao;
import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;


    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void transfer(int sender, int beneficiary, double amount) {

    }

    @Override
    public Users findById(long id) {
        // create query
        TypedQuery<Users> query = entityManager.createQuery(
                "select u from Users u "
                        + "where u.id = :id", Users.class
        );
        query.setParameter("id", id);

        // execute query
        Users user = query.getSingleResult();
        return user;
    }


    @Override
    @Transactional
    public void save(Users user) {
        entityManager.persist(user);
    }

    @Override
    public List<Users> allUsers() {
        return List.of();
    }


    @Override
    @Transactional
    public void update(Users user) {
        entityManager.merge(user);
    }


}
