package model;

import java.io.Serializable;

public class Delivery implements Serializable{
	private long d_id,user_id;
	private String nsin,delivery,pay;
	
	public Delivery() {
	}

	public Delivery(long user_id, String nsin, String delivery, String pay) {
		this.user_id = user_id;
		this.nsin = nsin;
		this.delivery = delivery;
		this.pay = pay;
	}

	public long getD_id() {
		return d_id;
	}

	public void setD_id(long d_id) {
		this.d_id = d_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getNsin() {
		return nsin;
	}

	public void setNsin(String nsin) {
		this.nsin = nsin;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}
	
}
