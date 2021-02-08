package com.t3bank.dao;

import java.util.List;

import com.t3bank.model.Account;


public interface AccountDAO {
	public void insertAccount(int user_id, double amount);
	public boolean updateAccount(int account_id, boolean approval);
	public Account selectAccount(int user_id);
	public List<Account> selectAllAccounts();
	public boolean deleteAccount(int account_id);
}

