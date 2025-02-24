package com.PayverdeAssessment.service.impl;

import com.PayverdeAssessment.dao.TransactionLogDao;
import com.PayverdeAssessment.dao.UserDao;
import com.PayverdeAssessment.dao.UserVirtualAccountDao;
import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.UserVirtualAccount;
import com.PayverdeAssessment.entity.Users;
import com.PayverdeAssessment.exceptions.CustomException;
import com.PayverdeAssessment.exceptions.CustomNotFoundException;
import com.PayverdeAssessment.repository.UserRepository;
import com.PayverdeAssessment.service.UserService;
import com.PayverdeAssessment.validation.AddFundValidation;
import com.PayverdeAssessment.validation.CreateUserValidation;
import com.PayverdeAssessment.validation.FetchTransactionLogsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    private final UserDao userDao;

    private final UserVirtualAccountDao userVirtualAccountDao;

    private final TransactionLogDao transactionLogDao;

    public UserServiceImpl(UserDao userDao, UserVirtualAccountDao userVirtualAccountDao, TransactionLogDao transactionLogDao) {
        this.userDao = userDao;
        this.userVirtualAccountDao = userVirtualAccountDao;
        this.transactionLogDao = transactionLogDao;
    }


    @Override
    public void save(CreateUserValidation createUserValidation) {
        Users user = new Users();

        user.setEmail(createUserValidation.getEmail());
        user.setFullName(createUserValidation.getFullName());
        user.setUserType("user");

        UserVirtualAccount userVirtualAccount = new UserVirtualAccount();
        userVirtualAccount.setBalance(100.0);
        userVirtualAccount.setCurrency("$");
        userVirtualAccount.setUser(user);
        user.setUserVirtualAccount(userVirtualAccount);

        userDao.save(user);
    }

    @Override
    public void transfer(long sender, long beneficiary, double amount) {
      Users senderUser = userDao.findById(sender);
      if(senderUser == null){
          throw new CustomNotFoundException("Sender not found");
      }

     UserVirtualAccount senderVirtualAccount = senderUser.getUserVirtualAccount();
      if(senderVirtualAccount.getBalance() < amount){
          throw new CustomException("Insufficient balance");
      }


        Users beneficiaryUser = userDao.findById(beneficiary);
        if(beneficiaryUser == null){
            throw new CustomNotFoundException("Beneficiary not found");
        }

        UserVirtualAccount beneficiaryVirtualAccount = beneficiaryUser.getUserVirtualAccount();

        /// new wallet balance ///
        double newSenderBalance = senderVirtualAccount.getBalance() - amount;
        double newBeneficiaryBalance = beneficiaryVirtualAccount.getBalance() + amount;


        /// Log transaction ///
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setBeneficiary(beneficiaryUser);
        transactionLog.setSender(senderUser);
        transactionLog.setAmount(amount);
        transactionLog.setBeneficiaryBalanceAfter(newBeneficiaryBalance);
        transactionLog.setBeneficiaryBalanceBefore(beneficiaryVirtualAccount.getBalance());
        transactionLog.setSenderBalanceAfter(newSenderBalance);
        transactionLog.setSenderBalanceBefore(senderVirtualAccount.getBalance());


        /// Save transaction ///
        transactionLogDao.update(transactionLog);


        /// set new balance ///
        senderVirtualAccount.setBalance(newSenderBalance);
        beneficiaryVirtualAccount.setBalance(newBeneficiaryBalance);

        /// update new balance ///
        userVirtualAccountDao.update(senderVirtualAccount);
        userVirtualAccountDao.update(beneficiaryVirtualAccount);

    }

    @Override
    public void fundWallet(AddFundValidation addFundValidation) {

        Users beneficiaryUser = userDao.findById(addFundValidation.getBeneficiaryId());
        if(beneficiaryUser == null){
            throw new CustomNotFoundException("Beneficiary not found");
        }

        UserVirtualAccount beneficiaryVirtualAccount = beneficiaryUser.getUserVirtualAccount();

        /// new wallet balance ///
        double newBeneficiaryBalance = beneficiaryVirtualAccount.getBalance() + addFundValidation.getAmount();


        /// Log transaction ///
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setBeneficiary(beneficiaryUser);
        transactionLog.setSender(beneficiaryUser);
        transactionLog.setAmount(addFundValidation.getAmount());
        transactionLog.setBeneficiaryBalanceAfter(newBeneficiaryBalance);
        transactionLog.setBeneficiaryBalanceBefore(beneficiaryVirtualAccount.getBalance());
        transactionLog.setSenderBalanceAfter(newBeneficiaryBalance);
        transactionLog.setSenderBalanceBefore(beneficiaryVirtualAccount.getBalance());

        /// Save transaction ///
        transactionLogDao.update(transactionLog);

        /// set new balance ///
        beneficiaryVirtualAccount.setBalance(newBeneficiaryBalance);

        /// update new balance ///
        userVirtualAccountDao.update(beneficiaryVirtualAccount);
    }


}
