package com.t3bank.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.sun.org.slf4j.internal.Logger;
import com.t3bank.dao.DAOImpl;
import com.t3bank.model.Account;
import com.t3bank.model.Customer;
import com.t3bank.model.Transfer;
import com.t3bank.utility.HelloLogger;

/**
 * Servlet implementation class CustControlServlet
 */
public class CustControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(HelloLogger.class);
	private static DAOImpl dao = null;
	private static Customer customer = new Customer();

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			dao = new DAOImpl();
		} catch (Exception e) {
			LOGGER.warn("Problem: custControlServlet init()");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustControlServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read command parameter
			// int command = Integer.parseInt(request.getParameter("command"));
			String command = request.getParameter("command");
			if (command == null) {
				customerAccountList(request, response);
			} else if (command.equalsIgnoreCase("INSERTACCOUNT")) {
				insertAccount(request, response);
			} else if (command.equalsIgnoreCase("ACCOUNTLIST")) {
				customerAccountList(request, response);
			} else if (command.equalsIgnoreCase("SELECTACCOUNT")) {
				selectCustomerAccount(request, response);
			} else if (command.equalsIgnoreCase("MAKEDEPOSIT")) {
				makeDeposit(request, response);
			} else if (command.equalsIgnoreCase("MAKEWITHDRAWAL")) {
				makeWithdrawal(request, response);
			} else if (command.equalsIgnoreCase("MAKETRANSFER")) {
				makeTransfer(request, response);
			} else if (command.equalsIgnoreCase("APPROVETRANSFER")) {
				approveTransfer(request, response);
			} else if (command.equalsIgnoreCase("REGISTRATION")) {
				insertUser(request, response);
			} else if (command.equalsIgnoreCase("DENYTRANSFER")) {
				denyTransfer(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Customer customer = new Customer();
		customer.setFirst_name(request.getParameter("first_name"));
		customer.setLast_name(request.getParameter("last_name"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		dao.insertUser(request.getParameter("password"), request.getParameter("first_name"), request.getParameter("last_name"), request.getParameter("email"), 1);
		String email = customer.getEmail();
		Customer tc = new Customer();
		tc = dao.selectNewCustomer(email);
		HttpSession session = request.getSession();
		session.setAttribute("user", tc);	 
		customerAccountList(request, response);
		
		
	}

	private void denyTransfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get the transfer id from form data
		int transfer_id = Integer.parseInt(request.getParameter("transfer_id"));
		Transfer transfer = dao.selectTransfer(transfer_id);
		// submit response to dao to update relations
		dao.updateTransfer(transfer.getTransfer_id(), transfer.getSender_id(), transfer.getTarget_id(),
				transfer.getAmount(), false);
		// redirect user back to customer-home
		customerAccountList(request, response);
	}


	private void approveTransfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get the transfer id from form data
		int transfer_id = Integer.parseInt(request.getParameter("transfer_id"));
		Transfer transfer = dao.selectTransfer(transfer_id);
		// submit response to dao to update relations
		dao.updateTransfer(transfer.getTransfer_id(), transfer.getSender_id(), transfer.getTarget_id(),
				transfer.getAmount(), true);
		// redirect user back to customer-home
		customerAccountList(request, response);
	}

	private void makeTransfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get transfer info from form data
		Account acc1 = dao.selectAccount(Integer.parseInt(request.getParameter("account_id")));
		double amount = Double.parseDouble(request.getParameter("amount"));
		Account acc2 = dao.selectAccount(Integer.parseInt(request.getParameter("target_account")));
		// process transfer and log to DB
		dao.insertTransfer(acc1, acc2, amount);
		// redirect to customer home
		customerAccountList(request, response);

	}

	private void makeWithdrawal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account acc = dao.selectAccount(Integer.parseInt(request.getParameter("account_id")));
		double amount = Double.parseDouble(request.getParameter("amount"));
		boolean success = dao.makeWithdrawal(acc, amount);
		if (success) {
			// redirect to customer home
			customerAccountList(request, response);
		} else {
			String message = "Invalid Withdrawal was canceled";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("account-view.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void makeDeposit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account acc = dao.selectAccount(Integer.parseInt(request.getParameter("account_id")));
		double amount = Double.parseDouble(request.getParameter("amount"));
		boolean success = dao.makeDeposit(acc, amount);
		if (success) {
			// redirect to customer home
			customerAccountList(request, response);
		} else {
			String message = "Invalid deposit was canceled";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("account-view.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void selectCustomerAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get account information from form data
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		// get the account from the DB
		Account acc = dao.selectAccount(account_id);
		acc.setTransactionList(dao.selectAccountTransactions(account_id));
		// Place account in requestAttribute
		request.setAttribute("theAccount", acc);
		request.setAttribute("acc_transactions", acc.getTransactionList());
		// send to jsp page account-view.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/account-view.jsp");
		dispatcher.forward(request, response);
	}

	private void insertAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		customer = (Customer) session.getAttribute("user");
		// read new account info from form da
		int user_id = customer.getUser_id();
		LOGGER.info("user_id == " + user_id);
		double amount = Double.parseDouble(request.getParameter("amount"));
		// create a new account object and add to DB pending accounts
		dao.insertAccount(user_id, amount);
		// redirect to customer home
		customerAccountList(request, response);
	}

	private void customerAccountList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		customer = (Customer) session.getAttribute("user");
		// Get customer accounts from dao
		List<Account> accList = dao.selectCustomerAccounts(customer);
		List<Transfer> transferList = dao.selectCustomerTransfers(customer);

		// LOGGER.info("Number of accounts in accList: " + accounts.size()); //TO DO
		// figure out why JSTL stopped reading populating account list
		// Add accounts to request
		request.setAttribute("account_list", accList);
		request.setAttribute("customer_pending_transfers", transferList);
		// Send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}