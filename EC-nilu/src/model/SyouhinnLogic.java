package model;

import java.util.List;

import dao.SyouhinnDAO;


public class SyouhinnLogic {
	public List<Syouhinn> execute() {
		SyouhinnDAO dao = new SyouhinnDAO();
		List<Syouhinn> syouhinnList = dao.findByAll();
		return syouhinnList;
	}
	public List<Syouhinn> incart(Syouhinn syouhinn) {
		SyouhinnDAO dao = new SyouhinnDAO();
		List<Syouhinn> incartList = dao.findByIncart(syouhinn);
		return incartList;
	}
}
