package com.PayverdeAssessment.dao;

import com.PayverdeAssessment.entity.TransactionLog;

public interface TransactionLogDao {

    void save(TransactionLog transactionLog);

    void update(TransactionLog transactionLog);

}
