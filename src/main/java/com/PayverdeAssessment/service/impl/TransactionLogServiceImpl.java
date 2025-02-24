package com.PayverdeAssessment.service.impl;

import com.PayverdeAssessment.dao.TransactionLogDao;
import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.repository.TransactionRepository;
import com.PayverdeAssessment.repository.UserRepository;
import com.PayverdeAssessment.service.TransactionLogService;
import com.PayverdeAssessment.validation.FetchTransactionLogsValidation;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {


    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private UserRepository userRepository;


    private final TransactionLogDao transactionLogDao;

    public TransactionLogServiceImpl(TransactionLogDao transactionLogDao) {
        this.transactionLogDao = transactionLogDao;
    }



    @Override
    public List<TransactionLog> fetchTransactionLog(FetchTransactionLogsValidation fetchTransactionLogsValidation) {
        List<TransactionLog> userTransactionLogs = new ArrayList<>();

        transactionRepository.findAll().stream().forEach(data -> {
            if(data.getSender().getId().equals(fetchTransactionLogsValidation.getUserId()) || data.getBeneficiary().getId().equals(fetchTransactionLogsValidation.getUserId())){
                data.getBeneficiary().setUserVirtualAccount(null);
                data.getSender().setUserVirtualAccount(null);
                userTransactionLogs.add(data);
            }
        });

        return userTransactionLogs;
    }
}
