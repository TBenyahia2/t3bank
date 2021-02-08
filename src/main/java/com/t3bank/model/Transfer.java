package com.t3bank.model;
public class Transfer {
	
	private int transfer_id = -1;
	private int sender_id = -1;
	private int target_id = -1;
	private double amount = 0.00;
	private boolean is_approved = false;
	
	public Transfer() {
		super();
	}

	public int getTransfer_id() {
		return transfer_id;
	}

	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}

	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}

	public int getTarget_id() {
		return target_id;
	}

	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isIs_approved() {
		return is_approved;
	}

	public void setIs_approved(boolean is_approved) {
		this.is_approved = is_approved;
	}
}
