package com.PayverdeAssessment.dao.impl;

import com.PayverdeAssessment.dao.TransactionLogDao;
import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TransactionLogDaoImpl implements TransactionLogDao {

    private final EntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionLogDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(TransactionLog transactionLog) {
        entityManager.persist(transactionLog);
    }

    @Override
    @Transactional
    public void update(TransactionLog transactionLog) {
        entityManager.merge(transactionLog);
    }


}
