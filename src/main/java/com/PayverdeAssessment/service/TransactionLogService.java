package com.PayverdeAssessment.service;

import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;
import com.PayverdeAssessment.validation.FetchTransactionLogsValidation;

import java.util.List;

public interface TransactionLogService {
    List<TransactionLog> fetchTransactionLog(FetchTransactionLogsValidation fetchTransactionLogsValidation);

}
