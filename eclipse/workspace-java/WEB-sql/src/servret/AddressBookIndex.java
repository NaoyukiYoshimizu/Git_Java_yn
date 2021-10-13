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
import model.Seach;
import model.Users;

/**
 * Servlet implementation class AddressBookIndex
 */
@WebServlet("/AddressBookIndex")
public class AddressBookIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// AdressBookListリスト作成
		GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
		List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
		request.setAttribute("AdressBookList", AdressBookList);

		String errorMsg = "";
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
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
		String text = request.getParameter("text");
		String columns = request.getParameter("operator");
		String done = request.getParameter("done");
		// リクエストパラメータチェック
		if (done.equals("編集")) {
			GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
			List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
			request.setAttribute("AdressBookList", AdressBookList);

			// edit用フォワード
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
			dis.forward(request, response);
		} else {
			String errorMsg = "";
			if (text == null || text.length() == 0) {
				errorMsg += "空白です<br>";
			}
			if (text.matches("[0-9]{1,}") && columns.equals("name")) {
				errorMsg += "全角で入力してください";
			} else if (text.matches("[a-zA-Z]{1,}") && (columns.equals("id") || columns.equals("age"))) {
				errorMsg += "数字で入力してください";
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);

			if (text.length() != 0 && errorMsg.length() == 0) {

				// 入力値をプロパティに設定
				Seach Seach1 = new Seach(columns, text);
				PostAdressLogic postAdressLogic = new PostAdressLogic();
				List<Seach> findSeachList = postAdressLogic.execute(Seach1);

				for (int i = 0; i < findSeachList.size(); i++) {
					if (findSeachList.get(i).getAddress().matches("error.*")) {
						errorMsg += findSeachList.get(i).getAddress() + "\nもしくは入力値を見直してください<br>";
					}
				}
				// リクエストスコープに保存
				request.setAttribute("findSeachList", findSeachList);
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);

			// フォワード
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
			dis.forward(request, response);
		}
	}
}
