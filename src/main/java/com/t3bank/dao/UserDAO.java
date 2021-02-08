package com.t3bank.dao;

import java.util.List;

import com.t3bank.model.User;



public interface UserDAO {
	public void insertUser(String password, String first_name, String last_name, String email, int role);
	public boolean updateUser(int user_id, String first_name, String last_name);
	public User selectUser(int user_id);
	public List<User> selectAllUsers();
	public boolean deleteUser(int user_id);	
}