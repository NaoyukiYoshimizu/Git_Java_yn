package model;

import java.util.List;

import dao.WorkDAO;

public class GetWorkLogic {
	public List<Work> execute(Work work) {
		WorkDAO dao = new WorkDAO();
		List<Work> atdList = dao.findByOwn(work);
		return atdList;
	}
	public List<Work> findAll(Work work) {
		WorkDAO dao = new WorkDAO();
		List<Work> allList = dao.findAll(work);
		return allList;
	}
}
