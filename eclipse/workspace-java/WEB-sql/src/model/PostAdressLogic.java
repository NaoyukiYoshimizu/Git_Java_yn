package model;

import dao.AddressDAO;

public class PostAdressLogic {
	public void execute(AdressBook adressBook) {
		AddressDAO dao = new AddressDAO();
		dao.select(adressBook);
	}
}
