package com.t3bank.dao;

import java.util.List;

import com.t3bank.model.Transaction;



public interface TransactionDAO {
	public void insertTransaction(int account_id, int user_id, double amount);
	public boolean updateTransaction(int transaction_id, boolean approval);
	public Transaction selectTransaction(int transaction_id);
	public List<Transaction> selectAllTransactions();
	public boolean deleteTransaction(int transaction_id);
	

}

