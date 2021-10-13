package servret;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

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
 * Servlet implementation class AddressBookEdit
 */
@WebServlet("/AddressBookEdit")
public class AddressBookEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// AdressBookList作成
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
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
		String newname = request.getParameter("newname");
		String newage = request.getParameter("newage");
		String newaddress = request.getParameter("newaddress");
		String before = request.getParameter("before");
		String after = request.getParameter("after");
		String choose = request.getParameter("choose");
		// 名前のパターンの生成（姓名を入力、間に全角空白）
		// 「[^ -~｡-ﾟ]+」は「全角以上1回以上の繰り返し」
		Pattern namePattern = Pattern.compile("^[^ -~｡-ﾟ]+[　][^ -~｡-ﾟ]+$");

		// リクエストパラメータチェック
		String errorMsg = "";
		if (done.equals("新規作成")) {
			if (newname == null || newname.length() == 0) {
				errorMsg += "名前が入力されていません<br>";
			} else if (namePattern.matcher(newname).matches()) {
				errorMsg += "";
			} else {
				errorMsg += "名前が全角で入力されていません<br>";
			}
			if (newage.matches("[a-zA-Z]{1,}")) {
				errorMsg += "数字で入力してください";
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			// 入力値をプロパティに設定
			int age = Integer.parseInt(newage);
			AdressBook adressBook = new AdressBook(newname, age, newaddress);
			PostAdressLogic postAdressLogic = new PostAdressLogic();
			postAdressLogic.create(adressBook);
		} else if (done.equals("更新")) {
			if (before == null || before.length() == 0) {
				errorMsg += "空白です<br>";
			}
			if (before.matches("[0-9]{1,}") && choose.equals("name")) {
				errorMsg += "全角で入力してください";
			} else if (before.matches("[a-zA-Z]{1,}") && (choose.equals("id") || choose.equals("age"))) {
				errorMsg += "数字で入力してください";
			}
			if (after == null || after.length() == 0) {
				errorMsg += "空白です<br>";
			}
			// AdressBookList作成
			GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
			List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
			if (after.matches("[0-9]{1,}") && choose.equals("name")) {
				errorMsg += "全角で入力してください";
			} else if (after.matches("[a-zA-Z]{1,}") && (choose.equals("id") || choose.equals("age"))) {
				errorMsg += "数字で入力してください";
			} else if (choose.equals("id")) {
				for (AdressBook adressbook : AdressBookList) {
					int afterint = Integer.parseInt(after);
					if (adressbook.getId() == afterint) {
						errorMsg += "既にある数字です";
						break;
					}
				}
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			if (before.length() != 0 && after.length() != 0 && errorMsg.length() == 0) {
				// 入力値をプロパティに設定
				AdressBook adressBook = new AdressBook(before, after, choose);
				PostAdressLogic postAdressLogic = new PostAdressLogic();
				postAdressLogic.update(adressBook);
			}
		} else {
			if (text == null || text.length() == 0) {
				errorMsg += "空白です<br>";
			}
			if (text.matches("[a-zA-Z]{1,}") && columns.equals("id")) {
				errorMsg += "数字で入力してください";
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			if (text.length() != 0 && errorMsg.length() == 0) {
				// 入力値をプロパティに設定
				AdressBook adressBook = new AdressBook(columns, text);
				PostAdressLogic postAdressLogic = new PostAdressLogic();
				postAdressLogic.remove(adressBook);
			}
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// AdressBookList作成
		GetAdressListLogic getAdressBookListLogic = new GetAdressListLogic();
		List<AdressBook> AdressBookList = getAdressBookListLogic.execute();
		request.setAttribute("AdressBookList", AdressBookList);
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dis.forward(request, response);
	}
}
