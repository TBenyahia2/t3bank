package com.t3bank.dao;

import java.util.List;

import com.t3bank.model.Account;
import com.t3bank.model.Transfer;



public interface TransferDAO {
	public boolean insertTransfer(Account acc1, Account acc2, double amount);
	public boolean updateTransfer(int transfer_id, int sender, int target, double amount, boolean approval);
	public Transfer selectTransfer(int transfer_id);
	public List<Transfer> selectAllTransfers();
	public boolean deleteTransfer(int transfer_id);
}
