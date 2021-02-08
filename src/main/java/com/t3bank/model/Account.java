package com.t3bank.model;

import java.util.List;

public class Account {
	private int account_id = -1;
	private double balance = -1.00d;
	private int user_id = -1;
	private boolean isApproved = false;
	private List<Transaction> transactionList = null;

	public Account() {
		super();
	}

	public int getAccount_id() {
		return account_id;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
