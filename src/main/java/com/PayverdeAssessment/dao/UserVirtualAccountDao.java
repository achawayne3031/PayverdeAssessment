package com.PayverdeAssessment.dao;

import com.PayverdeAssessment.entity.UserVirtualAccount;
import com.PayverdeAssessment.entity.Users;

public interface UserVirtualAccountDao {

    void save(UserVirtualAccount userVirtualAccount);

    void update(UserVirtualAccount userVirtualAccount);
}
