package com.PayverdeAssessment.dao;

import com.PayverdeAssessment.entity.TransactionLog;
import com.PayverdeAssessment.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void transfer(int sender, int beneficiary, double amount);

    Users findById(long id);

    void save(Users user);

    List<Users> allUsers();

    void update(Users user);

}
