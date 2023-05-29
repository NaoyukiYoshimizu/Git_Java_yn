package model;

import java.util.List;

import dao.SyouhinnDAO;


public class SyouhinnLogic {
	public List<Syouhinn> execute() {
		SyouhinnDAO dao = new SyouhinnDAO();
		List<Syouhinn> syouhinnList = dao.findByAll();
		return syouhinnList;
	}
	/*public boolean execute(Syouhinn syouhinn) {
		SyouhinnDAO dao = new SyouhinnDAO();
		Syouhinn syouhinn = dao.findBySypuhin(syouhinn);
		return syouhinn != null;

	}*/
}
