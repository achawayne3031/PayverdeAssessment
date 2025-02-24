package com.PayverdeAssessment.controller;


import com.PayverdeAssessment.config.ApiResponse;
import com.PayverdeAssessment.dao.TransactionLogDao;
import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;
import com.PayverdeAssessment.exceptions.CustomException;
import com.PayverdeAssessment.repository.UserRepository;
import com.PayverdeAssessment.service.TransactionLogService;
import com.PayverdeAssessment.service.UserService;
import com.PayverdeAssessment.validation.AddFundValidation;
import com.PayverdeAssessment.validation.CreateUserValidation;
import com.PayverdeAssessment.validation.FetchTransactionLogsValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;
    private final TransactionLogService transactionLogService;

   private final TransactionLogDao transactionLogDao;

    public UserController(UserService userService, TransactionLogService transactionLogService, TransactionLogDao transactionLogDao) {
        this.userService = userService;
        this.transactionLogService = transactionLogService;
        this.transactionLogDao = transactionLogDao;
    }

    @PostMapping("/fetch-transaction-logs")
    public ResponseEntity fetchTransactionLog(@RequestBody @Valid FetchTransactionLogsValidation fetchTransactionLogsValidation){
        List<TransactionLog> transData = transactionLogService.fetchTransactionLog(fetchTransactionLogsValidation);

        return new ResponseEntity<>(new ApiResponse<Object>("user transaction logs.", true, transData),
                HttpStatus.OK);
    }


    @PostMapping("/add-fund")
    public ResponseEntity addFund(@RequestBody @Valid AddFundValidation addFundValidation){
        if (addFundValidation.getAmount() <= 0) {
            throw new CustomException("amount must be more than 0");
        }


        //// Fund ////
        userService.fundWallet(addFundValidation);
        return new ResponseEntity<>(new ApiResponse<Object>("Account funded.", true),
                HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Valid CreateUserValidation createUserValidation){
        if (userRepository.existsByEmail(createUserValidation.getEmail())) {
            return new ResponseEntity<>(new ApiResponse<Object>("Email already registered", true), HttpStatus.OK);
        }

        //// Save ////
        userService.save(createUserValidation);
        return new ResponseEntity<>(new ApiResponse<Object>("User created.", true),
                HttpStatus.OK);
    }


}
