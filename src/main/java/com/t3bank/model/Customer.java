package com.t3bank.model;
import java.util.List;

public class Customer extends User {
	private List<Account> accounts = null;
	private Account account = null;

	public Customer() {
		super();
		super.setRole(1);
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}	
}