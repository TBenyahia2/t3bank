package com.t3bank.model;
public class Transaction {
	private int transaction_id = -1;
	private double amount = -1.00d;
	private int account_id = -1;
	private int user_id = -1;
	private int target_account_id = -1;
	private boolean is_approved = false;
	
	public Transaction() {
		super();
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getTarget_account_id() {
		return target_account_id;
	}

	public void setTarget_account_id(int target_account_id) {
		this.target_account_id = target_account_id;
	}

	public boolean isIs_approved() {
		return is_approved;
	}

	public void setIs_approved(boolean is_approved) {
		this.is_approved = is_approved;
	}
}
