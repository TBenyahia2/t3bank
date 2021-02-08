package com.t3bank.model;

public class UserFactory {
	
	public User makeUser(int role) {
		//User user = null;
		if(role == 1) {
			return new Customer();
		}
		else if(role == 2) {
			return new Employee();
		}
		else return null;
	}

}

