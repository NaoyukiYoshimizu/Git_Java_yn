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
	public void detail(Syouhinn syouhinn) {
		SyouhinnDAO dao = new SyouhinnDAO();
		dao.detail(syouhinn);
	}
	public boolean create(Syouhinn syouhinn) {
		SyouhinnDAO dao = new SyouhinnDAO();
		if (dao.create(syouhinn)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean delete(Syouhinn syouhinn) {
		SyouhinnDAO dao = new SyouhinnDAO();
		if (dao.delete(syouhinn)) {
			return true;
		} else {
			return false;
		}
	}
}
