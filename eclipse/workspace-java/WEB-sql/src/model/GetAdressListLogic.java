package model;

import java.util.List;

import dao.AddressDAO;

public class GetAdressListLogic {
	public List<AdressBook> execute() {
		AddressDAO dao = new AddressDAO();
		List<AdressBook> adressBookList = dao.findAll();
		return adressBookList;
	}
}
