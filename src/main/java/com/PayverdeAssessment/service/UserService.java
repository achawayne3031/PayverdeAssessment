package com.PayverdeAssessment.service;

import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;
import com.PayverdeAssessment.validation.AddFundValidation;
import com.PayverdeAssessment.validation.CreateUserValidation;
import com.PayverdeAssessment.validation.FetchTransactionLogsValidation;

import java.util.List;

public interface UserService {

    void save(CreateUserValidation createUserValidation);

    void transfer(long sender, long beneficiary, double amount);

    void fundWallet(AddFundValidation addFundValidation);
}
