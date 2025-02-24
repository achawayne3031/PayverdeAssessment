package com.PayverdeAssessment.repository;

import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionLog, Long> {


}
