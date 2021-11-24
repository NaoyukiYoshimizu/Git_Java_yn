package model;

import java.util.List;

import dao.AddressDAO;

public class PostAdressLogic {
	public List<Seach> execute(Seach Seach) {
		AddressDAO dao = new AddressDAO();
		List<Seach> findSeachList = dao.findByAdress(Seach);
		return findSeachList;
	}

	public void create(AdressBook adressBook) { 
		AddressDAO dao = new AddressDAO();
		dao.create(adressBook);
	}
	public void remove(AdressBook adressBook) { 
		AddressDAO dao = new AddressDAO();
		dao.remove(adressBook);
	}
	public void update(AdressBook adressBook) { 
		AddressDAO dao = new AddressDAO();
		dao.update(adressBook);
	}
}
