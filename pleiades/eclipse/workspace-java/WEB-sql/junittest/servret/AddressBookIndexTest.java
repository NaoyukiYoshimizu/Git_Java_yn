package servret;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.AdressBook;
import model.Seach;

class AddressBookIndexTest {
	MockHttpServletRequest req = new MockHttpServletRequest();
	MockHttpServletResponse resp = new MockHttpServletResponse();
	// MockHttpSession session = new MockHttpSession();
	AddressBookIndex servret = new AddressBookIndex();

	@BeforeEach
	void setUp() throws Exception {
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
		// session = new MockHttpSession();
		servret = new AddressBookIndex();
	}

	@Test
	void testDoGet() {
		try {
			req.setParameter("name", "yamada");
			req.setParameter("pass", "1234");
			Login login = new Login();
			login.doPost(req, resp);
			HttpSession session = req.getSession();
			servret.doGet(req, resp);
			assertEquals("/WEB-INF/jsp/main.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoGet_2() {
		try {

			servret.doGet(req, resp);
			assertEquals("/WEB-sql/", resp.getRedirectedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_msg1() {
		try {
			req.setParameter("text", "");
			req.setParameter("operator", "id");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("", req.getParameter("text"));
			assertEquals("id", req.getParameter("operator"));
			assertEquals("空白です<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertNull(findSeachList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_msg2() {
		try {
			req.setParameter("text", "1234");
			req.setParameter("operator", "name");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("1234", req.getParameter("text"));
			assertEquals("name", req.getParameter("operator"));
			assertEquals("全角で入力してください", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertNull(findSeachList);
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_msg3() {
		try {
			req.setParameter("text", "a");
			req.setParameter("operator", "id");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("a", req.getParameter("text"));
			assertEquals("id", req.getParameter("operator"));
			assertEquals("数字で入力してください", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertNull(findSeachList);
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_msg4() {
		try {
			req.setParameter("text", "a");
			req.setParameter("operator", "age");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("a", req.getParameter("text"));
			assertEquals("age", req.getParameter("operator"));
			assertEquals("数字で入力してください", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertNull(findSeachList);
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_1() {
		try {
			req.setParameter("text", "1");
			req.setParameter("operator", "id");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("1", req.getParameter("text"));
			assertEquals("id", req.getParameter("operator"));
			assertEquals("", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(1, findSeachList.size());
			for (int i = 0; i < findSeachList.size(); i++) {
				assertEquals(1, findSeachList.get(0).getId());
				assertEquals("山田　花子", findSeachList.get(0).getName());
				assertEquals(47, findSeachList.get(0).getAge());
				assertEquals("大阪府堺市北区○○町1-2-", findSeachList.get(0).getAddress());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_2() {
		try {
			req.setParameter("text", "山田　花子");
			req.setParameter("operator", "name");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("山田　花子", req.getParameter("text"));
			assertEquals("name", req.getParameter("operator"));
			assertEquals("", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(1, findSeachList.size());
			for (int i = 0; i < findSeachList.size(); i++) {
				assertEquals(1, findSeachList.get(0).getId());
				assertEquals("山田　花子", findSeachList.get(0).getName());
				assertEquals(47, findSeachList.get(0).getAge());
				assertEquals("大阪府堺市北区○○町1-2-", findSeachList.get(0).getAddress());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_3() {
		try {
			req.setParameter("text", "47");
			req.setParameter("operator", "age");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("47", req.getParameter("text"));
			assertEquals("age", req.getParameter("operator"));
			assertEquals("", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(1, findSeachList.size());
			for (int i = 0; i < findSeachList.size(); i++) {
				assertEquals(1, findSeachList.get(0).getId());
				assertEquals("山田　花子", findSeachList.get(0).getName());
				assertEquals(47, findSeachList.get(0).getAge());
				assertEquals("大阪府堺市北区○○町1-2-", findSeachList.get(0).getAddress());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_4() {
		try {
			req.setParameter("text", "大阪府堺市北区");
			req.setParameter("operator", "address");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("大阪府堺市北区", req.getParameter("text"));
			assertEquals("address", req.getParameter("operator"));
			assertEquals("", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(1, findSeachList.size());
			for (int i = 0; i < findSeachList.size(); i++) {
				assertEquals(1, findSeachList.get(0).getId());
				assertEquals("山田　花子", findSeachList.get(0).getName());
				assertEquals(47, findSeachList.get(0).getAge());
				assertEquals("大阪府堺市北区○○町1-2-", findSeachList.get(0).getAddress());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_5() {
		try {
			req.setParameter("text", "10");
			req.setParameter("operator", "id");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("10", req.getParameter("text"));
			assertEquals("id", req.getParameter("operator"));
			assertEquals("", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(0, findSeachList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_6() {
		try {
			req.setParameter("text", "10");
			req.setParameter("operator", "id");
			req.setParameter("done", "編集");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("10", req.getParameter("text"));
			assertEquals("id", req.getParameter("operator"));
			assertEquals("編集", req.getParameter("done"));
			assertEquals(null, req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/edit.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertNull(findSeachList);
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
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPost_例外() {
		try {
			req.setParameter("text", "abcd efghij");
			req.setParameter("operator", "name");
			req.setParameter("done", "検索");
			servret = new AddressBookIndex();
			servret.doPost(req, resp);
			assertEquals("abcd efghij", req.getParameter("text"));
			assertEquals("name", req.getParameter("operator"));
			assertEquals("検索", req.getParameter("done"));
			assertEquals("error name 全角で入力してください\nもしくは入力値を見直してください<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/Result.jsp", resp.getForwardedUrl());
			List<Seach> findSeachList = (List<Seach>) req.getAttribute("findSeachList");
			assertEquals(1, findSeachList.size());
			for (int i = 0; i < findSeachList.size(); i++) {
				assertEquals(0, findSeachList.get(i).getId());
				assertEquals(null, findSeachList.get(0).getName());
				assertEquals(-1, findSeachList.get(0).getAge());
				assertEquals("error name 全角で入力してください", findSeachList.get(0).getAddress());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

}
