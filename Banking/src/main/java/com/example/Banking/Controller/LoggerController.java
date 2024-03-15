package com.example.Banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Banking.Model.Logger;
import com.example.Banking.Service.LoggerService;

@RestController
@RequestMapping("/logger")
public class LoggerController {
	@Autowired
	private LoggerService loggerService;

	// addLog
	public void addLog(Logger logger) {
		loggerService.addLog(logger);
	}

	// showLog
	@GetMapping("/findByAccountId/{acctID}")
	public Logger showLog(@PathVariable int acctID) {
		return loggerService.showLog(acctID);
	}

	public void deleteLog(int acctID) {
		loggerService.deleteLog(acctID);
	}
}
