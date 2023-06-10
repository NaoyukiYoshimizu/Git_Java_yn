package model;

import dao.DeliveryDAO;

public class DeliveryLogic {
	public boolean create(Delivery delivery) {
		DeliveryDAO dao = new DeliveryDAO();
		if (dao.create(delivery)) {
			return true;
		} else {
			return false;
		}
	}
}
