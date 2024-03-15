package com.example.Banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Banking.Dao.AccountRepository;
import com.example.Banking.Model.Accounts;
import com.example.Banking.Model.Logger;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private LoggerService loggerService;

	public String saveAccount(Accounts account) {
		accountRepository.save(account);
		return "Account added successfully..!";
	}

	public Accounts getAccountInfo(int acctID) {
		return accountRepository.findById(acctID).orElse(null);
	}

	public void deleteAccount(int acctID) {
		accountRepository.deleteById(acctID);
	}

	public int getBalance(int acctID) {
		return accountRepository.findBalanceByAcctID(acctID);
	}

	public String depositAmount(int acctID, int amount) {
		int initBal = getBalance(acctID);
		Logger logger = new Logger();
		logger.setAcctID(acctID);
		logger.setTransacType("Deposited");
		logger.setTransacStatus("Success");
		logger.setInitBal(initBal);
		logger.setFinalBal(initBal + amount);
		loggerService.addLog(logger);
		accountRepository.saveBalanceByAcctID(acctID, amount);
		return "Amount " + amount + " deposited to " + acctID;

	}

	public String withdrawAmount(int acctID, int amount) {
		int initBal = getBalance(acctID);
		if (initBal < amount)
			return "Please enter amount less than main balance. Your balance is "+initBal+ " ruppess only";
		Logger logger = new Logger();
		logger.setAcctID(acctID);
		logger.setTransacType("Withdrawn");
		logger.setTransacStatus("Success");
		logger.setInitBal(initBal);
		logger.setFinalBal(initBal - amount);

		loggerService.addLog(logger);
		accountRepository.withdrawAmountByAcctID(acctID, amount);
		return "Amount " + amount + " withdrwan by " + acctID;
	}

	public String transferAmount(int acctID, int destAcctID, int amount) {
		int initBalSender = getBalance(acctID);		
		if (initBalSender < amount)
			return "Please enter amount less than main balance. Your balance is "+initBalSender+ " ruppess only";
		
		int initBalReceiver = getBalance(destAcctID);

		Logger loggerSender = new Logger();
		loggerSender.setAcctID(acctID);
		loggerSender.setTransacType("Transferred");
		loggerSender.setTransacStatus("Success");
		loggerSender.setInitBal(initBalSender);
		loggerSender.setFinalBal(initBalSender - amount);
		loggerService.addLog(loggerSender);

		Logger loggerReceiver = new Logger();
		loggerReceiver.setAcctID(destAcctID);
		loggerReceiver.setTransacType("Received");
		loggerReceiver.setTransacStatus("Success");
		loggerReceiver.setInitBal(initBalReceiver);
		loggerReceiver.setFinalBal(initBalReceiver + amount);
		loggerService.addLog(loggerReceiver);

		accountRepository.withdrawAmountByAcctID(acctID, amount);
		accountRepository.saveBalanceByAcctID(destAcctID, amount);

		return "Amount " + amount + " tranfered from " + acctID + " to " + destAcctID;
	}

}
