package servret;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdressBook;
import model.GetAdressListLogic;
import model.PostAdressLogic;
import model.Users;

/**
 * Servlet implementation class AddressBookIndex
 */
@WebServlet("/AddressBookIndex")
public class AddressBookIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
		List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
		request.setAttribute("AdressBookList", AdressBookList);

		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");

		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/WEB-sql/");
		} else { // ログイン済み
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("num1");
		String seach = request.getParameter("operator");

		// リクエストパラメータチェック
		String errorMsg = "";
		if (text == null || text.length() == 0) {
			errorMsg += "最初空白だよ<br>";
		}

		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		if (text.length() != 0 ) {
			// 入力値をプロパティに設定
			AdressBook filterAdressBook = new AdressBook(text,seach);
			PostAdressLogic postAdressLogic = new PostAdressLogic();
			postAdressLogic.execute(filterAdressBook);
			// リクエストスコープに保存
			request.setAttribute("filterAdressBook", filterAdressBook);
		}
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher("/AdressBookCheckResult.jsp");
		dis.forward(request, response);
	}
}
