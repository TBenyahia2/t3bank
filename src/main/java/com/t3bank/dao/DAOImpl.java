package com.t3bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.t3bank.model.Account;
import com.t3bank.model.Customer;
import com.t3bank.model.Transaction;
import com.t3bank.model.Transfer;
import com.t3bank.model.User;
import com.t3bank.model.UserFactory;
import com.t3bank.utility.DBConnector;

public class DAOImpl implements UserDAO, TransferDAO, AccountDAO, TransactionDAO {

	private UserFactory uF = new UserFactory();
	private Connection conn = DBConnector.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static final Logger LOGGER = LogManager.getLogger(DAOImpl.class);

	public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem: Error closing db resources. DAOImpl closeResources()");
			// e.printStackTrace();
		}
	}

	public void insertTransaction(int account_id, int user_id, double amount) {
		try {
			ps = conn.prepareStatement(
					"INSERT INTO public.transactions(" + " account_id, user_id, amount)" + "	VALUES (?, ?, ?);");
			ps.setInt(1, account_id);
			ps.setInt(2, user_id);
			ps.setDouble(3, amount);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Insert Transaction Error caught in SQL exception block");
			// e.printStackTrace();
		}
	}

	public boolean updateTransaction(int transaction_id, boolean approval) {
		try {
			ps = conn.prepareStatement(
					"UPDATE public.transactions" + "	SET is_approved=?" + "	WHERE transaction_id=?;");
			ps.setBoolean(1, approval);
			ps.setInt(2, transaction_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Error in update transaction DAOImlp");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public Transaction selectTransaction(int transaction_id) {
		Transaction transaction = new Transaction();
		try {
			ps = conn.prepareStatement("SELECT transaction_id, account_id, user_id, amount, is_approved"
					+ "	FROM public.transactions WHERE transaction_id = ?;");
			ps.setInt(1, transaction_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				transaction.setTransaction_id(transaction_id);
				transaction.setAccount_id(rs.getInt(2));
				transaction.setUser_id(rs.getInt(3));
				transaction.setAmount(rs.getDouble(4));
				transaction.setIs_approved(rs.getBoolean(5));
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectTransaction SQLException.");
			// e.printStackTrace();
			return null;
		}
		return transaction;
	}

	public List<Transaction> selectAllTransactions() {
		List<Transaction> tList = new ArrayList<Transaction>();
		try {
			ps = conn.prepareStatement("SELECT transaction_id, account_id, user_id, amount FROM public.transactions;");
			rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransaction_id(rs.getInt(1));
				transaction.setAccount_id(rs.getInt(2));
				transaction.setUser_id(rs.getInt(3));
				transaction.setAmount(rs.getDouble(4));
				transaction.setIs_approved(true);
				tList.add(transaction);
				// System.out.println("Number of Transactions" + tList.size());
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllAccounts SQLException.");
			// e.printStackTrace();
			return null;
		}
		return tList;
	}

	public boolean deleteTransaction(int transaction_id) {
		try {
			ps = conn.prepareStatement("DELETE FROM public.transactions" + "	WHERE transaction_id = ?");
			ps.setInt(1, transaction_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl deleteTransaction SQLException.");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public void insertAccount(int user_id, double amount) {
		try {
			ps = conn.prepareStatement("INSERT INTO public.accounts(" + "	user_id, balance)" + "	VALUES (?, ?);");
			ps.setInt(1, user_id);
			ps.setDouble(2, amount);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Create User Error caught in SQL exception block");
			// e.printStackTrace();
		}
	}

	public boolean updateAccount(int account_id, boolean approval) {
		try {
			ps = conn.prepareStatement("UPDATE public.accounts SET is_approved=? WHERE account_id = ?;");
			ps.setBoolean(1, approval);
			ps.setInt(2, account_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Error in update account DAOImlp");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public Account selectAccount(int account_id) {
		Account acc = new Account();
		try {
			ps = conn.prepareStatement(
					"SELECT user_id, balance, is_approved FROM public.accounts WHERE account_id = ?;");
			ps.setInt(1, account_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				acc.setAccount_id(account_id);
				acc.setUser_id(rs.getInt(1));
				acc.setBalance(rs.getDouble(2));
				acc.setApproved(rs.getBoolean(3));
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectUser SQLException.");
			// e.printStackTrace();
			return null;
		}
		return acc;
	}

	public List<Account> selectAllAccounts() {
		// Account acc = new Account();
		List<Account> accList = new ArrayList<Account>();
		try {
			ps = conn.prepareStatement(
					"SELECT account_id, user_id, balance, is_approved FROM public.accounts ORDER BY user_id ASC;");
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				acc.setAccount_id(rs.getInt(1));
				acc.setUser_id(rs.getInt(2));
				acc.setBalance(rs.getDouble(3));
				acc.setApproved(rs.getBoolean(4));
				// System.out.println(acc.getAccount_id() +" "+ acc.getUser_id());
				accList.add(acc);
				// System.out.println("Account Just added: " +acc.getAccount_id());
				// System.out.println(accList.get(0).getAccount_id() + "Size "+accList.size());
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllAccounts SQLException.");
			// e.printStackTrace();
			return null;
		}
		// System.out.println(accList.get(0).getAccount_id()+"
		// "+accList.get(1).getAccount_id()+" "+accList.get(2).getAccount_id());
		// System.out.println(accList.get(3).getAccount_id()+"
		// "+accList.get(4).getAccount_id()+" "+accList.get(5).getAccount_id());
		return accList;
	}

	public boolean deleteAccount(int account_id) {
		try {
			ps = conn.prepareStatement("DELETE FROM public.accounts" + "	WHERE account_id = ?");
			ps.setInt(1, account_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl deleteAccount SQLException.");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public void insertUser(String password, String first_name, String last_name, String email, int role) {
		try {
			ps = conn.prepareStatement("INSERT INTO public.users(password, first_name, last_name, email, role)"
					+ "	VALUES (crypt(?, gen_salt('bf')), ?, ?, ?, ?);");
			ps.setString(1, password);
			ps.setString(2, first_name);
			ps.setString(3, last_name);
			ps.setString(4, email);
			ps.setInt(5, role); // to be taken from hidden form when registering new user or employee from page
			ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warn("Problem in insertUser daoImpl");
			// e.printStackTrace();
		}

	}

	public boolean updateUser(int user_id, String first_name, String last_name) {
		try {
			ps = conn.prepareStatement(
					"UPDATE public.users" + "	SET first_name=?, last_name=?" + "	WHERE user_id = ?;");
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			ps.setInt(3, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			LOGGER.warn("Error in update user DAOImlp");
			return false;
		}
		return true;
	}

	public User selectUser(int user_id) {
		User user = null;
		try {
			ps = conn.prepareStatement("SELECT user_id, password, first_name, last_name, email, role"
					+ "	FROM public.users" + "	WHERE user_id = ?;");
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = uF.makeUser(rs.getInt(6));
				user.setUser_id(rs.getInt(1));
				user.setPassword(rs.getString(2));
				user.setFirst_name(rs.getString(3));
				user.setLast_name(rs.getString(4));
				user.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectUser SQLException.");
			// e.printStackTrace();
			return null;
		}
		return user;
	}

	public Customer selectNewCustomer(String email) {
		Customer customer = new Customer();
		try {
			ps = conn.prepareStatement("SELECT user_id, password, first_name, last_name, email, role"
					+ "	FROM public.users WHERE email = ?;");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				customer.setUser_id(rs.getInt(1));
				customer.setPassword(rs.getString(2));
				customer.setFirst_name(rs.getString(3));
				customer.setLast_name(rs.getString(4));
				customer.setEmail(rs.getString(5));
				customer.setRole(1);
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectUser SQLException.");
			// e.printStackTrace();
			return null;
		}
		return customer;
	}

	public List<User> selectAllUsers() {
		List<User> userList = new ArrayList<User>();
		User user = null;
		try {
			ps = conn.prepareStatement(
					"SELECT user_id, password, first_name, last_name, email, role FROM public.users ORDER BY user_id;");
			rs = ps.executeQuery();
			while (rs.next()) {
				user = uF.makeUser(rs.getInt(6));
				user.setUser_id(rs.getInt(1));
				user.setPassword(rs.getString(2));
				user.setFirst_name(rs.getString(3));
				user.setLast_name(rs.getString(4));
				user.setEmail(rs.getString(5));
				// System.out.println("User First Name: " + user.getFirst_name());
				userList.add(user);
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllUsers SQLException.");
			// e.printStackTrace();
			return null;
		}
		return userList;
	}

	public boolean deleteUser(int user_id) {
		try {
			ps = conn.prepareStatement("DELETE FROM public.users" + "	WHERE user_id = ?");
			ps.setInt(1, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl deleteUser SQLException.");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertTransfer(Account acc1, Account acc2, double amount) {
		try {
			if (amount < 0.00 || amount > acc1.getBalance()) {
				return false; // can't send negative money or send more than posess
			}
			CallableStatement cstmt = conn.prepareCall("CALL transfer(?, ?, CAST(? AS NUMERIC))");
			cstmt.setInt(1, acc1.getAccount_id());
			cstmt.setDouble(3, amount);
			cstmt.setInt(2, acc2.getAccount_id());
			cstmt.execute();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl insertTransfer");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateTransfer(int transfer_id, int sender, int target, double amount, boolean approval) {
		try {
			// User selects to approve or not, If approved is set to true make deposit to
			// target and deleteTransfer from table
			if (approval == true) {
				CallableStatement cstmt = conn.prepareCall("CALL deposit(?, CAST(? AS NUMERIC), ?)");
				cstmt.setInt(1, target);
				cstmt.setDouble(2, amount);
				cstmt.setInt(3, selectAccount(target).getUser_id());
				cstmt.execute();
				deleteTransfer(transfer_id);
			} else if (approval == false) {
				CallableStatement cstmt = conn.prepareCall("CALL deposit(?, CAST(? AS NUMERIC), ?)");
				cstmt.setInt(1, sender);
				cstmt.setDouble(2, amount);
				cstmt.setInt(3, selectAccount(sender).getUser_id());
				cstmt.execute();
				deleteTransfer(transfer_id);
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl updateTransfer");
			// e.printStackTrace();
			return false;
		} finally {
			deleteTransfer(transfer_id);
		}
		return true;
	}

	public Transfer selectTransfer(int transfer_id) {
		Transfer transfer = new Transfer();
		try {
			ps = conn.prepareStatement(
					"SELECT sender_id, target_id, amount, is_approved FROM public.transfers WHERE transfer_id = ?;");
			ps.setInt(1, transfer_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				transfer.setTransfer_id(transfer_id);
				transfer.setSender_id(rs.getInt(1));
				transfer.setTarget_id(rs.getInt(2));
				transfer.setAmount(rs.getDouble(3));
				transfer.setIs_approved(rs.getBoolean(4));

			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectTransfer SQLException.");
			// e.printStackTrace();
			return null;
		}
		return transfer;
	}

	public List<Transfer> selectAllTransfers() {
		Transfer transfer = new Transfer();
		List<Transfer> transferList = new ArrayList<Transfer>();
		try {
			ps = conn.prepareStatement("SELECT transfer_id FROM public.transfers;");
			rs = ps.executeQuery();
			while (rs.next()) {
				transfer = selectTransfer(rs.getInt(1));
				transferList.add(transfer);
				System.out.println("Number of Accounts" + transferList.size());
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllTransfers SQLException.");
			// e.printStackTrace();
			return null;
		}
		return transferList;
	}

	public boolean deleteTransfer(int transfer_id) {
		try {
			ps = conn.prepareStatement("DELETE FROM public.transfers WHERE transfer_id = ?");
			ps.setInt(1, transfer_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl deleteTransfer SQLException.");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean makeDeposit(Account account, double amount) {
		try {
			if (amount < 0.00) {
				return false; // can't deposit negative money
			}
			CallableStatement cstmt = conn.prepareCall("CALL deposit(?, CAST(? AS NUMERIC), ?)");
			cstmt.setInt(1, account.getAccount_id());
			cstmt.setDouble(2, amount);
			cstmt.setInt(3, account.getUser_id());
			cstmt.execute();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl makeDeposit");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean makeWithdrawal(Account account, double amount) {
		try {
			if (amount < 0.00 || amount > account.getBalance()) {
				return false; // can't withdraw negative money or withdraw more than posess
			}
			CallableStatement cstmt = conn.prepareCall("CALL withdrawal(?, CAST(? AS NUMERIC), ?)");
			cstmt.setInt(1, account.getAccount_id());
			cstmt.setDouble(2, amount);
			cstmt.setInt(3, account.getUser_id());
			cstmt.execute();
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl makeWithdrawal");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Account> selectCustomerAccounts(Customer customer) {

		List<Account> accList = new ArrayList<Account>();
		try {
			ps = conn.prepareStatement(
					"SELECT account_id, user_id, balance, is_approved FROM public.accounts WHERE user_id=? AND is_approved=true;");
			ps.setInt(1, customer.getUser_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				acc.setAccount_id(rs.getInt(1));
				acc.setUser_id(rs.getInt(2));
				acc.setBalance(rs.getDouble(3));
				acc.setApproved(rs.getBoolean(4));
				// System.out.println(acc.getAccount_id() +" "+ acc.getUser_id());
				accList.add(acc);
				// System.out.println("accList.get(i): Userid "+accList.get(i).getUser_id()+"
				// Account: "+accList.get(i).getAccount_id());
			}
			// return accList;
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectCustomerAccounts SQLException.");
			// e.printStackTrace();
			return null;
		}
		// System.out.println("SelectCustomerAccounts size : " + accList.size()+ "
		// "+accList.get(1).getAccount_id()+" "+ accList.get(2).getAccount_id());
		return accList;
	}

	public List<Transfer> selectCustomerTransfers(Customer customer) {
		List<Account> caList = selectCustomerAccounts(customer);
		// System.out.println("# customer accounts : " +caList.size());
		// System.out.println("caList:\n1: " +caList.get(1).getAccount_id()+ "\n2:
		// "+caList.get(2).getAccount_id());
		List<Transfer> transferList = new ArrayList<Transfer>();
		for (Account acc : caList) {
			// System.out.println("User" +acc.getUser_id()+ " Account#"
			// +acc.getAccount_id());
			try {
				ps = conn.prepareStatement(
						"SELECT transfer_id, sender_id, target_id, amount FROM public.transfers WHERE target_id = ? AND is_approved = false;");
				// System.out.println("Account ID in while loop" + acc.getAccount_id());
				ps.setInt(1, acc.getAccount_id());
				rs = ps.executeQuery();
				while (rs.next()) {
					Transfer transfer = new Transfer();
					transfer.setTransfer_id(rs.getInt(1));
					transfer.setSender_id(rs.getInt(2));
					transfer.setTarget_id(rs.getInt(3));
					transfer.setAmount(rs.getDouble(4));
					// System.out.println(transfer.getSender_id() +" "+ transfer.getTarget_id());
					transferList.add(transfer);
					// System.out.println("Number of transfers" + transferList.size());
				}
			} catch (SQLException e) {
				LOGGER.warn("Problem : DAOImpl selectAllTransfers SQLException.");
				// e.printStackTrace();
				return null;
			}
		}
		// System.out.println(transferList.get(0).getTransfer_id() +" Txid's "+
		// transferList.get(1).getTransfer_id() );
		return transferList;
	}

	public User validateUser(int user_id, String password) {
		User user = null;
		try {
			ps = conn.prepareStatement(
					"SELECT first_name, last_name, email, role FROM public.users WHERE user_id = ? AND password = crypt(?, password);");
			ps.setInt(1, user_id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = uF.makeUser(rs.getInt(4));
				user.setUser_id(user_id);
				user.setFirst_name(rs.getString(1));
				user.setLast_name(rs.getString(2));
				user.setEmail(rs.getString(3));
			}
		} catch (SQLException e) {
			LOGGER.error("userLogin Error caught in SQL exception block");
			// e.printStackTrace();
		}
		return user;
	}

	public List<Account> selectPendingAccounts() {
		// Account acc = new Account();
		List<Account> accList = new ArrayList<Account>();
		try {
			ps = conn.prepareStatement(
					"SELECT account_id, user_id, balance, is_approved FROM public.accounts WHERE is_approved=false ORDER BY user_id ASC;");
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				acc.setAccount_id(rs.getInt(1));
				acc.setUser_id(rs.getInt(2));
				acc.setBalance(rs.getDouble(3));
				acc.setApproved(rs.getBoolean(4));
				// System.out.println(acc.getAccount_id() +" "+ acc.getUser_id());
				accList.add(acc);
				// System.out.println("Account Just added: " +acc.getAccount_id());
				// System.out.println(accList.get(0).getAccount_id() + "Size "+accList.size());
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllAccounts SQLException.");
			// e.printStackTrace();
			return null;
		}
		// System.out.println(accList.get(0).getAccount_id()+"
		// "+accList.get(1).getAccount_id()+" "+accList.get(2).getAccount_id());
		// System.out.println(accList.get(3).getAccount_id()+"
		// "+accList.get(4).getAccount_id()+" "+accList.get(5).getAccount_id());
		return accList;
	}

	public List<Transaction> selectAccountTransactions(int account_id) {
		List<Transaction> tList = new ArrayList<Transaction>();
		try {
			ps = conn.prepareStatement(
					"SELECT transaction_id, account_id, user_id, amount, is_approved FROM public.transactions WHERE account_id=?;");
			ps.setInt(1, account_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransaction_id(rs.getInt(1));
				transaction.setAccount_id(rs.getInt(2));
				transaction.setUser_id(rs.getInt(3));
				transaction.setAmount(rs.getDouble(4));
				transaction.setIs_approved(rs.getBoolean(5));
				tList.add(transaction);
				// System.out.println("Number of Transactions" + tList.size());
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllAccounts SQLException.");
			// e.printStackTrace();
			return null;
		}
		return tList;
	}

	public List<User> selectAllCustomers() {
		List<User> userList = new ArrayList<User>();
		User user = null;
		try {
			ps = conn.prepareStatement(
					"SELECT user_id, password, first_name, last_name, email, role FROM public.users WHERE role=1 ORDER BY user_id;");
			rs = ps.executeQuery();
			while (rs.next()) {
				user = uF.makeUser(rs.getInt(6));
				user.setUser_id(rs.getInt(1));
				user.setPassword(rs.getString(2));
				user.setFirst_name(rs.getString(3));
				user.setLast_name(rs.getString(4));
				user.setEmail(rs.getString(5));
				// System.out.println("User First Name: " + user.getFirst_name());
				userList.add(user);
			}
		} catch (SQLException e) {
			LOGGER.warn("Problem : DAOImpl selectAllUsers SQLException.");
			// e.printStackTrace();
			return null;
		}
		return userList;
	}

}
