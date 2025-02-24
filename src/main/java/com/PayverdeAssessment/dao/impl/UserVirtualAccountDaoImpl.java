package com.PayverdeAssessment.dao.impl;

import com.PayverdeAssessment.dao.UserVirtualAccountDao;
import com.PayverdeAssessment.entity.UserVirtualAccount;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserVirtualAccountDaoImpl implements UserVirtualAccountDao {

    private final EntityManager entityManager;

    public UserVirtualAccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(UserVirtualAccount userVirtualAccount) {
        entityManager.merge(userVirtualAccount);
    }

    @Override
    @Transactional
    public void update(UserVirtualAccount userVirtualAccount) {
        entityManager.merge(userVirtualAccount);
    }
}
