package com.PayverdeAssessment;

import com.PayverdeAssessment.dao.UserDao;
import com.PayverdeAssessment.entity.UserVirtualAccount;
import com.PayverdeAssessment.entity.Users;
import com.PayverdeAssessment.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayverdeAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayverdeAssessmentApplication.class, args);
	}



	@Bean
	public CommandLineRunner commandLineRunner(UserDao userDao, UserService userService){
		return runner -> {
			//  createDummyMerchant(userDao);
			//	dummyTransaction(userService);
		};
	}

	private void dummyTransaction(UserService userService){
		userService.transfer(3, 4, 10);
	}



	private void createDummyMerchant(UserDao userDao) {
		Users user = new Users();
		user.setEmail("waynemerchantee@gmail.com");
		user.setFullName("wayne Merchant");
		user.setVerified(true);
		user.setUserType("merchant");

		/// Set virtual Account ////
		UserVirtualAccount userVirtualAccount = new UserVirtualAccount();
		userVirtualAccount.setBalance(0);
		userVirtualAccount.setCurrency("$");
		user.setUserVirtualAccount(userVirtualAccount);

		userDao.save(user);
	}


}
