package test;

import java.util.List;

import dao.AddressDAO;
import model.PostAdressLogic;
import model.Seach;

public class AddressbookLogicTest {
	public static void main(String[] args) {
		testFindBySeach1();
		testFindBySeach2();
		testExecute1();
		testExecute2();
	}

	public static void testFindBySeach1() {
		String text = "name";
		String seach = "山田　花子";
		Seach Seach = new Seach(text,seach);
		AddressDAO dao = new AddressDAO();
		List<Seach> result = dao.findByAdress(Seach);
		if (result != null) {
			System.out.println("findBySeach1-1:成功しました");
			for(int i=0;i<result.size();i++){
				System.out.println("id="+result.get(i).getId());
			}
		} else {
			System.out.println("findBySeach1-1:失敗しました");
		}
		Seach = new Seach("id", "2");
		dao = new AddressDAO();
		result = dao.findByAdress(Seach);
		if (result != null) {
			System.out.println("findBySeach1-2:成功しました");
			if(result.size() == 0) {
				System.out.println("empty");
			}
			for(int i=0;i<result.size();i++){
				System.out.println("Address="+result.get(i).getAddress());
			}
			
		} else {
			System.out.println("findBySeach1-2:失敗しました");
		}
	}

	public static void testFindBySeach2() {
		String text = "id";
		String seach = "1";
		Seach Seach = new Seach(text,seach);
		AddressDAO dao = new AddressDAO();
		List<Seach>  result = dao.findByAdress(Seach);
		if (result != null) {
			System.out.println("findBySeach2:成功しました");
			for(int i=0;i<result.size();i++){
				System.out.println("id="+result.get(i).getId());
			}
		} else {
			System.out.println("findBySeach2:失敗しました");
		}
	}

	public static void testExecute1() {
		String text = "id";
		String seach = "4";
		Seach Seach = new Seach(text,seach);
		PostAdressLogic bo = new PostAdressLogic();
		List<Seach> findSeachList = bo.execute(Seach);
		if (findSeachList != null) {
			System.out.println("testExecute1:成功しました");
			if(findSeachList.size() == 0) {
				System.out.println("empty");
			}
			for(int i=0;i<findSeachList.size();i++){
				System.out.println("id="+findSeachList.get(i).getId());
			}
		} else {
			System.out.println("testExecute1:失敗しました");
		}
	}
	public static void testExecute2() {
		String text = "id";
		String seach = "2";
		Seach Seach = new Seach(text,seach);
		PostAdressLogic bo = new PostAdressLogic();
		List<Seach> findSeachList = bo.execute(Seach);
		if (findSeachList != null)  {
			System.out.println("testExecute2:成功しました");
			if(findSeachList.size() == 0) {
				System.out.println("empty");
			}
			for(int i=0;i<findSeachList.size();i++){
				System.out.println("id="+findSeachList.get(i).getId());
			}
		} else {
			System.out.println("testExecute2:失敗しました");
		}
	}
}