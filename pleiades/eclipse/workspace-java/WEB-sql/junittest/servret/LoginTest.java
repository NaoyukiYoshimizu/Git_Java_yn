package servret;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.Users;

class LoginTest {
	MockHttpServletRequest req = new MockHttpServletRequest();
	MockHttpServletResponse resp = new MockHttpServletResponse();
	// MockHttpSession session = new MockHttpSession();
	Login servret = new Login();

	@BeforeEach
	void setUp() throws Exception {
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
		// session = new MockHttpSession();
		servret = new Login();
	}

	@Test
	void testDoPostLogin_msg1() {
		try {
			req.setParameter("name", "");
			req.setParameter("pass", "1234");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("", req.getParameter("name"));
			assertEquals("1234", req.getParameter("pass"));
			assertEquals("IDが入力されていません<br>ユーザー名またはパスワードが違います<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPostLogin_msg2() {
		try {
			req.setParameter("name", "yyy");
			req.setParameter("pass", "1234");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("yyy", req.getParameter("name"));
			assertEquals("1234", req.getParameter("pass"));
			assertEquals("IDが半角英数4文字以上で入力されていません<br>ユーザー名またはパスワードが違います<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPostLogin_msg3() {
		try {
			req.setParameter("name", "1234");
			req.setParameter("pass", "");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("1234", req.getParameter("name"));
			assertEquals("", req.getParameter("pass"));
			assertEquals("パスワードが入力されていません<br>ユーザー名またはパスワードが違います<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPostLogin_msg4() {
		try {
			req.setParameter("name", "1234");
			req.setParameter("pass", "123");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("1234", req.getParameter("name"));
			assertEquals("123", req.getParameter("pass"));
			assertEquals("パスワードが半角英数4～8文字で入力されていません<br>ユーザー名またはパスワードが違います<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPostLogin_msg5() {
		try {
			req.setParameter("name", "satou");
			req.setParameter("pass", "1234");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("satou", req.getParameter("name"));
			assertEquals("1234", req.getParameter("pass"));
			assertEquals("ユーザー名またはパスワードが違います<br>", req.getAttribute("errorMsg"));
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Test
	void testDoPostLogin_1() {
		try {
			req.setParameter("name", "yamada");
			req.setParameter("pass", "1234");
			servret = new Login();
			servret.doPost(req, resp);
			assertEquals("yamada", req.getParameter("name"));
			assertEquals("1234", req.getParameter("pass"));
			assertEquals("", req.getAttribute("errorMsg"));
			HttpSession session = req.getSession();
			// session.setAttribute("loginUser", users);
			// assertNotNull(session.getAttribute("loginUser") );
			assertEquals("/WEB-INF/jsp/loginResult.jsp", resp.getForwardedUrl());
			Users loginUser = (Users) session.getAttribute("loginUser");
			assertEquals("yamada", loginUser.getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

}
