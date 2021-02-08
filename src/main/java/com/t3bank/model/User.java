package com.t3bank.model;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
public abstract class User {
	
	private int user_id = -1;
	private String password = "default";
	private String first_name = "default";
	private String last_name = "default";
	private String email = "default@default.com";
	private int role = -1;
	//private static final Logger LOGGER = LogManager.getLogger(HelloLogger.class);
	
	public User() {
		super();
	}

	public int getUser_id() {
		return user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User:\n user_id=" + user_id + "\nfirst_name=" + first_name + "\nlast_name="
				+ last_name + "\nemail=" + email + "\nrole=" + role + "\n\n";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
