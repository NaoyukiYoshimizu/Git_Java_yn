package model;

import java.util.List;

import dao.WorkDAO;
import dao.WorkEditDAO;

public class PostWorkLogic {
	public boolean create(Work work) {
		WorkDAO dao = new WorkDAO();
		if (dao.create(work)) {
			return true;
		}else {
			return false;
		}
	}
	public List<Work> monthexecute(Work work) {
		WorkDAO dao = new WorkDAO();
		List<Work> selectList = dao.findByMouth(work);
		return selectList;
	}
	public boolean update(Work work) {
		WorkDAO dao = new WorkDAO();
		if (dao.update(work)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean timeupdate(Work work) {
		WorkEditDAO dao = new WorkEditDAO();
		if (dao.timeupdate(work)) {
			return true;
		}else {
			return false;
		}
	}
}
