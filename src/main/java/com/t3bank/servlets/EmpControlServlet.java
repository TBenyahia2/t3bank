package com.t3bank.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.t3bank.dao.DAOImpl;
import com.t3bank.model.Account;
import com.t3bank.model.Customer;
import com.t3bank.model.Transaction;
import com.t3bank.model.Transfer;
import com.t3bank.model.User;
import com.t3bank.utility.HelloLogger;

/**
 * Servlet implementation class EmpControlServlet
 */
public class EmpControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(HelloLogger.class);	
	private static DAOImpl dao = null;
	
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list all users
		try {
			String command = request.getParameter("command");
			//String commando = request.getParameter("commando");
			if (command == null) {
				listUsers(request, response);
			} else if (command.equalsIgnoreCase("APPROVEACCOUNT")) {
				approveAccount(request, response);
			} else if (command.equalsIgnoreCase("DENYACCOUNT")) {
				denyAccount(request, response);
			} else if (command.equalsIgnoreCase("SELECTCUSTOMER")) { 
				viewCustomer(request, response);
			} else if (command.equalsIgnoreCase("PENDINGACCOUNTS")) {
				listPendingAccounts(request, response);
			} else if (command.equalsIgnoreCase("TRANSACTIONLIST")) {
				listTransactions(request,response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	private void listTransactions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transaction> tList = null;
		tList = dao.selectAllTransactions();
		request.setAttribute("transaction_list", tList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction-list.jsp");
		dispatcher.forward(request, response);
	}

	private void denyAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		dao.updateAccount(account_id, false);
		dao.deleteAccount(account_id);
		listUsers(request,response);		
	}

	private void approveAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		dao.updateAccount(account_id, true);	
		listUsers(request,response);
	}

	private void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get data from form		
		Customer customer = (Customer) dao.selectUser(Integer.parseInt(request.getParameter("user_id")));
		List<Account> accList = dao.selectCustomerAccounts(customer);
		List<Transfer> transferList = dao.selectCustomerTransfers(customer);		
		//set the customer and account list into dispatcher
		request.setAttribute("customer", customer);
		request.setAttribute("cust_account_view", accList);
		request.setAttribute("cust_transfer_list", transferList);
		//line dispatcher to view-customer.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-view.jsp");
		//forward to destination
		dispatcher.forward(request, response);
	}

	private void listPendingAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Make a container
		List<Account> accList = dao.selectPendingAccounts();		
		//set into dispatcher
		request.setAttribute("pending_account_list", accList);
		//line up dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/account-approval.jsp");
		//forward
		dispatcher.forward(request, response);
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		List<User> userList = dao.selectAllCustomers();
		request.setAttribute("user_list", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employee-home.jsp");
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
