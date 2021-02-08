package com.t3bank.test;

import java.sql.Connection;
import java.util.List;

//Make sure to be using the junit.jupiter.api for JUNIT5
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.t3bank.dao.DAOImpl;
import com.t3bank.model.Account;
import com.t3bank.model.Customer;
import com.t3bank.model.Transaction;
import com.t3bank.model.Transfer;
import com.t3bank.model.User;
import com.t3bank.model.UserFactory;
import com.t3bank.utility.DBConnector;
import com.t3bank.utility.HelloLogger;

import nl.altindag.log.LogCaptor;

public class T3BankTest {
	private static DAOImpl dao;
	private static UserFactory uF;
	private static User user;
	private static Connection conn;

	@BeforeAll
	public static void theSetUpEnvironment() {
		//conn = DBConnector.getConnection();
		dao = new DAOImpl();
		uF = new UserFactory();
	}

	@Test
	public void testAddMeTest() {
		HelloLogger hL = new HelloLogger();
		Assertions.assertEquals(10, hL.testAddMe(5, 5));
		Assertions.assertEquals(15, hL.testAddMe(5, 10));
		Assertions.assertNotEquals(25, hL.testAddMe(7, 14));
	}

	@Test
	public void getConnectionTest() {
		Connection conn = DBConnector.getConnection();
		Assertions.assertNotNull(conn);
	}

	@Test
	public void logMessagesActiveTest() {
		LogCaptor logCaptor = LogCaptor.forClass(HelloLogger.class);
		HelloLogger hL = new HelloLogger();
		hL.logCheck();
		Assertions.assertEquals("[Info Log]", logCaptor.getInfoLogs().toString());
		Assertions.assertEquals("[Debug Log]", logCaptor.getDebugLogs().toString());
		Assertions.assertEquals("[Warn Log]", logCaptor.getWarnLogs().toString());
		Assertions.assertEquals("[Error Log]", logCaptor.getErrorLogs().toString());
	}

	@Test
	public void userFactoryTest() {
		User customer = uF.makeUser(1);
		User employee = uF.makeUser(2);
		Assertions.assertNull(uF.makeUser(3));
		Assertions.assertEquals(1, customer.getRole());
		Assertions.assertEquals(2, employee.getRole());
	}

	@Test
	public void userDeleteTest() {
		Assertions.assertTrue(dao.deleteUser(10));
	}

	@Test
	public void selectAllUsersTest() {
		List<User> userList = dao.selectAllUsers();
		// System.out.println("There are " +userList.size()+" users in the List.");
		Assertions.assertFalse(userList.isEmpty());
	}

	@Test
	public void selectUserTest() {
		user = dao.selectUser(7);
		String first_name = user.getFirst_name();
		String last_name = user.getLast_name();
		String email = user.getEmail();
		Assertions.assertEquals("Roundhouse", first_name);
		Assertions.assertEquals("Chuck", last_name);
		Assertions.assertEquals("KickChucky@email.com", email);

	}

	@Test
	public void accountDeleteTest() {
		Assertions.assertTrue(dao.deleteAccount(107));
	}

	@Test
	public void transactionDeleteTest() {
		Assertions.assertTrue(dao.deleteTransaction(10));
	}

	/*
	 * Cant think of a way to let these tests run and be able to check dynamically
	 * without cluttering DB for other testing. Tested once.
	 * 
	 * @Test public void insertUserTest() { //dao.insertUser("password", "Junit",
	 * "Insert", "test@test.com", 1); }
	 * 
	 * @Test public void insertAccountTest() { //dao.insertAccount(7, 7357); }
	 */

	/*
	 * @Test public void insertTransactionTest() { dao.insertTransaction(81, 7,
	 * 7357); }
	 */

	@Test
	public void selectAccountTest() {
		Account acc = dao.selectAccount(82);
		// System.out.println(acc.getAccount_id());
		int account_id = acc.getAccount_id();
		int user_id = acc.getUser_id();
		double balance = acc.getBalance();
		Assertions.assertEquals(82, account_id);
		Assertions.assertEquals(8, user_id);
		Assertions.assertEquals(151.51, balance);
	}

	@Test
	public void selectAllAccountsTest() {
		List<Account> accList = dao.selectAllAccounts();
		System.out.println("There are " +accList.size()+" accounts in the List.");
		System.out.println(accList.get(1).toString());
		System.out.println(accList.get(2).toString());
		System.out.println(accList.get(3).toString());
		Assertions.assertFalse(accList.isEmpty());
	}

	@Test
	public void selectTransactionTest() {
		Transaction t = dao.selectTransaction(78);
		// System.out.println(acc.getAccount_id());
		int account_id = t.getAccount_id();
		int user_id = t.getUser_id();
		Assertions.assertEquals(81, account_id);
		Assertions.assertEquals(7, user_id);
		// Assertions.assertEquals(32.00, amount);
	}

	@Test
	public void selectAllTransactionTest() {
		List<Transaction> tList = dao.selectAllTransactions();
		// System.out.println("There are " +tList.size()+" transactions in the List.");
		Assertions.assertFalse(tList.isEmpty());
	}

	@Test
	public void updateUserTest() {
		dao.updateUser(8, "Updated1", "ByJUnit2");
		user = dao.selectUser(8);
		Assertions.assertEquals("Updated1", user.getFirst_name());
		Assertions.assertEquals("ByJUnit2", user.getLast_name());
	}

	@Test
	public void updateAccountTest() {
		dao.updateAccount(83, true);
		Account acc = dao.selectAccount(81);
		Assertions.assertEquals(true, acc.isApproved());
	}

//	@Test //might be unnecessary function/test
//	public void updateTransactionTest() {
//		dao.updateTransaction(78, true);
//		Transaction t = dao.selectTransaction(78);
//		Assertions.assertEquals(true, t.isIs_approved());
//	}
//
//	@Test
//	public void makeDepositTest() {
//		double prev_balance = dao.selectAccount(81).getBalance();
//		dao.makeDeposit(dao.selectAccount(81), 75.75);
//		Assertions.assertNotEquals(prev_balance, dao.selectAccount(81).getBalance());
//	}
//
//	@Test
//	public void makeWithdrawalTest() {
//		double prev_balance = dao.selectAccount(81).getBalance();
//		dao.makeWithdrawal(dao.selectAccount(81), 75.75);
//		Assertions.assertNotEquals(prev_balance, dao.selectAccount(81).getBalance());
//	}
//
//	@Test
//	public void insertTransferTest() {
//		double prev_balance = dao.selectAccount(87).getBalance();
//		dao.insertTransfer(dao.selectAccount(87), dao.selectAccount(99), 10.00);
//		Assertions.assertNotEquals(prev_balance, dao.selectAccount(87).getBalance());
//	}
//
//	@Test
//	public void updateTransferTrueTest() {
//		double prev_balance = dao.selectAccount(99).getBalance();
//		System.out.println(prev_balance + "prev bal");
//		dao.updateTransfer(24, 87, 99, 10.00, true);
//		Assertions.assertNotEquals(prev_balance, dao.selectAccount(99).getBalance());
//	}
//	@Test
//	public void updateTransferFalseTest() {
//		double prev_balance = dao.selectAccount(81).getBalance();
//		dao.updateTransfer(7, 81, 83, 75.75, false);
//		Assertions.assertNotEquals(prev_balance, dao.selectAccount(81).getBalance());
//	}
//	
//	
	@Test
	public void selectCustomerAccountsTest() {
		Customer customer = (Customer) dao.selectUser(7);
		//System.out.println("The customer: \nUser_id: " +customer.getUser_id());
		List<Account> caList = dao.selectCustomerAccounts(customer);
		//System.out.println(caList.toString());
		Assertions.assertFalse(caList.isEmpty());
	}
	@Test
	public void selectCustomerTransfersTest() {
		Customer customer = (Customer) dao.selectUser(15);
		//System.out.println("The customer User_id: " +customer.getUser_id());
		List<Transfer> ctList = dao.selectCustomerTransfers(customer);
		//System.out.println("User: " +customer.getUser_id()+ " Pending Transfers #: " +ctList.size());
		Assertions.assertTrue(ctList.isEmpty());
	}
	@Test
	public void validateUserTest() {
		User user = null;
		user = dao.validateUser(7, "password");
		Assertions.assertEquals(7, user.getUser_id());
		Assertions.assertEquals(1, user.getRole());
		
	}
	@Test
	public void wrongPasswordTest() {
		User user = null;
		user = dao.validateUser(7, "nooooo");
		Assertions.assertNull(user);
	}
	@AfterAll
	public static void breakDown() {
		//DBConnector.closeConnection(conn);
	}
}
