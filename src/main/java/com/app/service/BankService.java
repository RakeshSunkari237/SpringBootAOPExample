package com.app.service;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.app.annotation.MyAnnotation;

@Component
public class BankService {

	public String depositAmount(long amount) {
		System.out.println("amount depsiting.....");
		if (new Random().nextInt(10) > 5) {
			throw new RuntimeException("failure business logic..");
		} else {
			System.out.println("Amount deposited " + amount);
		}
		return "Amount Successfully credited";
	}

	@MyAnnotation
	public String addCustomer() {
		return "Customer added";
	}

}
