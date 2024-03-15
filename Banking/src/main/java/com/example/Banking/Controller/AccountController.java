package com.example.Banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Banking.Model.Accounts;
import com.example.Banking.Service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private LoggerController loggerController;

	// createAccount happens when we will createCustomer
	@PostMapping
	public String addAccount(@RequestBody Accounts account) {
		return accountService.saveAccount(account);
	}

	// checkBalance
	@GetMapping("/findBalanceById/{acctID}")
	public int getBalance(@PathVariable int acctID) {
		return accountService.getBalance(acctID);
	}

	// depositAmount
	@PutMapping("/depositAmountById/{acctID}/{amount}")
	public String depositAmount(@PathVariable int acctID, @PathVariable int amount) {		
		return accountService.depositAmount(acctID, amount);
		
	}

	// withdrawAmount
	@PutMapping("/withdrawAmountById/{acctID}/{amount}")
	public String withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
		return accountService.withdrawAmount(acctID, amount);
	}

	// transferAmount
	@PutMapping("/transferAmount/{acctID}/{destAcctID}/{amount}")
	public String transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {		
		return accountService.transferAmount(acctID, destAcctID, amount);	
		
	}

	// deleteAccount
	@DeleteMapping("/deleteById/{acctID}")
	public void deleteAccount(@PathVariable int acctID) {
		accountService.deleteAccount(acctID);
		loggerController.deleteLog(acctID);
	}

	// getAccountInfo
	@GetMapping("/findById/{acctID}")
	public Accounts getAccountInfo(@PathVariable int acctID) {
		return accountService.getAccountInfo(acctID);
	}

}
