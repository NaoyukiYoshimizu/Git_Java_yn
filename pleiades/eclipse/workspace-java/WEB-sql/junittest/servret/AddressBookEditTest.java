package servret;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.AdressBook;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressBookEditTest {
	MockHttpServletRequest req = new MockHttpServletRequest();
	MockHttpServletResponse resp = new MockHttpServletResponse();
	//MockHttpSession session = new MockHttpSession();
	AddressBookEdit servret = new AddressBookEdit();
	@BeforeEach
	void setUp() throws Exception {
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
		//session = new MockHttpSession();
		servret = new AddressBookEdit();
	}
	@Order(1)
	@Test
	void testDoGet() {
		try {
			req.setParameter("name","yamada");
			req.setParameter("pass","1234");
			Login login = new Login();
			login.doPost(req,resp);
		HttpSession session = req.getSession();
		servret.doGet(req,resp);
		assertEquals("/WEB-INF/jsp/edit.jsp", resp.getForwardedUrl() );
		List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
		assertNotNull(AdressBookList);
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("山田　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());

			assertEquals("大阪　太郎", AdressBookList.get(1).getName());
			assertEquals(71, AdressBookList.get(1).getAge());
			assertEquals("大阪府大阪市中央区道頓堀１丁目８?２５", AdressBookList.get(1).getAddress());

			assertEquals("唐木　崇行", AdressBookList.get(2).getName());
			assertEquals(60, AdressBookList.get(2).getAge());
			assertEquals("大阪府茨木市□□町4-5-6", AdressBookList.get(2).getAddress());

		}
		}catch(Exception e){
			e.printStackTrace();
			fail("例外検知");
		}
	}
	
	@Test
	void testDoGet_2() {
		try {

		servret.doGet(req,resp);
		assertEquals( "/WEB-sql/", resp.getRedirectedUrl() );
		}catch(Exception e){
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost新規_msg1() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","新規作成");
			req.setParameter("newname","");
			req.setParameter("newage","33");
			req.setParameter("newaddress","kyoto");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("新規作成", req.getParameter("done") );
			assertEquals( "", req.getParameter("newname") );
			assertEquals( "33", req.getParameter("newage") );
			assertEquals( "kyoto", req.getParameter("newaddress") );
			assertEquals( "名前が入力されていません<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
		}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost新規_msg2() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","新規作成");
			req.setParameter("newname","abcd");
			req.setParameter("newage","33");
			req.setParameter("newaddress","kyoto");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("新規作成", req.getParameter("done") );
			assertEquals( "abcd", req.getParameter("newname") );
			assertEquals( "33", req.getParameter("newage") );
			assertEquals( "kyoto", req.getParameter("newaddress") );
			assertEquals( "名前が全角で入力されていません<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
		}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost新規_msg3() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","新規作成");
			req.setParameter("newname","佐藤　一郎");
			req.setParameter("newage","a");
			req.setParameter("newaddress","kyoto");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("新規作成", req.getParameter("done") );
			assertEquals( "佐藤　一郎", req.getParameter("newname") );
			assertEquals( "a", req.getParameter("newage") );
			assertEquals( "kyoto", req.getParameter("newaddress") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
		}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost新規_msg4() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","新規作成");
			req.setParameter("newname","佐藤　一郎");
			req.setParameter("newage","-3");
			req.setParameter("newaddress","kyoto");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("新規作成", req.getParameter("done") );
			assertEquals( "佐藤　一郎", req.getParameter("newname") );
			assertEquals( "-3", req.getParameter("newage") );
			assertEquals( "kyoto", req.getParameter("newaddress") );
			assertEquals( "正の数で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Order(2)
	@Test
	void testDoPost新規_1() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","新規作成");
			req.setParameter("newname","佐藤　一郎");
			req.setParameter("newage","33");
			req.setParameter("newaddress","kyoto");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("新規作成", req.getParameter("done") );
			assertEquals( "佐藤　一郎", req.getParameter("newname") );
			assertEquals( "33", req.getParameter("newage") );
			assertEquals( "kyoto", req.getParameter("newaddress") );
			assertEquals( "", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
			assertEquals(4,AdressBookList.size());
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg1() {
		String ret = null;
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","大阪　太郎");
			req.setParameter("after","大阪　次朗");
			req.setParameter("id",ret);
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "大阪　太郎", req.getParameter("before") );
			assertEquals( "大阪　次朗", req.getParameter("after") );
			assertEquals( null, req.getParameter("id") );
			assertEquals( "name", req.getParameter("choose") );
			assertEquals( "idを選択してください<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg2() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","");
			req.setParameter("after","大阪　次朗");
			req.setParameter("id","2");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "", req.getParameter("before") );
			assertEquals( "大阪　次朗", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "name", req.getParameter("choose") );
			assertEquals( "旧が空白です<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg3() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","山田　花子");
			req.setParameter("after","1234");
			req.setParameter("id","1");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "山田　花子", req.getParameter("before") );
			assertEquals( "1234", req.getParameter("after") );
			assertEquals( "1", req.getParameter("id") );
			assertEquals( "name", req.getParameter("choose") );
			assertEquals( "全角で入力してください", req.getAttribute("errorMsg"));
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg4() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","2");
			req.setParameter("after","a");
			req.setParameter("id","2");
			req.setParameter("choose","id");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "2", req.getParameter("before") );
			assertEquals( "a", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "id", req.getParameter("choose") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg5() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","2");
			req.setParameter("after","-10");
			req.setParameter("id","2");
			req.setParameter("choose","id");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "2", req.getParameter("before") );
			assertEquals( "-10", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "id", req.getParameter("choose") );
			assertEquals( "正の数で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg6() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","2");
			req.setParameter("after","1");
			req.setParameter("id","2");
			req.setParameter("choose","id");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "2", req.getParameter("before") );
			assertEquals( "1", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "id", req.getParameter("choose") );
			assertEquals( "既にある数字です", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg7() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","71");
			req.setParameter("after","-72");
			req.setParameter("id","2");
			req.setParameter("choose","age");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "71", req.getParameter("before") );
			assertEquals( "-72", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "age", req.getParameter("choose") );
			assertEquals( "正の数で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg8() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","山田　花子");
			req.setParameter("after","");
			req.setParameter("id","1");
			req.setParameter("choose","id");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "山田　花子", req.getParameter("before") );
			assertEquals( "", req.getParameter("after") );
			assertEquals( "1", req.getParameter("id") );
			assertEquals( "id", req.getParameter("choose") );
			assertEquals( "新が空白です<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg9() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","1234");
			req.setParameter("after","山田　花子");
			req.setParameter("id","1");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "1234", req.getParameter("before") );
			assertEquals( "山田　花子", req.getParameter("after") );
			assertEquals( "1", req.getParameter("id") );
			assertEquals( "name", req.getParameter("choose") );
			assertEquals( "全角で入力してください", req.getAttribute("errorMsg"));
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg10() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","a");
			req.setParameter("after","10");
			req.setParameter("id","2");
			req.setParameter("choose","id");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "a", req.getParameter("before") );
			assertEquals( "10", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "id", req.getParameter("choose") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg11() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","a");
			req.setParameter("after","10");
			req.setParameter("id","2");
			req.setParameter("choose","age");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "a", req.getParameter("before") );
			assertEquals( "10", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "age", req.getParameter("choose") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost更新_msg12() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","2");
			req.setParameter("after","a");
			req.setParameter("id","2");
			req.setParameter("choose","age");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "2", req.getParameter("before") );
			assertEquals( "a", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "age", req.getParameter("choose") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	
	@Test
	void testDoPost更新_1() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","");
			req.setParameter("done","更新");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","大阪　太郎");
			req.setParameter("after","大阪　次朗");
			req.setParameter("id","2");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("更新", req.getParameter("done") );
			assertEquals( "大阪　太郎", req.getParameter("before") );
			assertEquals( "大阪　次朗", req.getParameter("after") );
			assertEquals( "2", req.getParameter("id") );
			assertEquals( "name", req.getParameter("choose") );
			assertEquals( "", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	@Test
	void testDoPost削除_msg1() {
		try {
			req.setParameter("text","");
			req.setParameter("operator","id");
			req.setParameter("done","削除");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("削除", req.getParameter("done") );
			assertEquals( "", req.getParameter("text") );
			assertEquals( "id", req.getParameter("operator") );
			assertEquals( "空白です<br>", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}

	@Test
	void testDoPost削除_msg2() {
		try {
			req.setParameter("text","a");
			req.setParameter("operator","id");
			req.setParameter("done","削除");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("削除", req.getParameter("done") );
			assertEquals( "a", req.getParameter("text") );
			assertEquals( "id", req.getParameter("operator") );
			assertEquals( "数字で入力してください", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
			
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}
	
	@Test
	void testDoPost削除_1() {
		try {
			req.setParameter("text","4");
			req.setParameter("operator","id");
			req.setParameter("done","削除");
			req.setParameter("newname","");
			req.setParameter("newage","");
			req.setParameter("newaddress","");
			req.setParameter("before","");
			req.setParameter("after","");
			req.setParameter("id","");
			req.setParameter("choose","name");
			servret = new AddressBookEdit();
			servret.doPost(req,resp);
			assertEquals("削除", req.getParameter("done") );
			assertEquals( "4", req.getParameter("text") );
			assertEquals( "id", req.getParameter("operator") );
			assertEquals( "", req.getAttribute("errorMsg") );
			assertEquals( "/WEB-INF/jsp/main.jsp", resp.getForwardedUrl() );
			List<AdressBook> AdressBookList = (List<AdressBook>) req.getAttribute("AdressBookList");
			assertNotNull(AdressBookList);
			assertEquals(3,AdressBookList.size());
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				fail("例外検知");
			}
	}

}
