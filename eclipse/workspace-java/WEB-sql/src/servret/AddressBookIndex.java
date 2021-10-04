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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/sample.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
