package com.t3bank.model;



public class Employee extends User{
	private double salary;
	private String title;
	
	public Employee() {
		super();
		super.setRole(2);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}