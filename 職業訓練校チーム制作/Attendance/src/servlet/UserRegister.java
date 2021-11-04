package servlet;

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

import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UserList作成
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();
		List<User> userList = registerUserLogic.execute();
		request.setAttribute("userList", userList);
		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/Attendance/");
		} else { // ログイン済み
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");
		String newid = request.getParameter("newid");
		String newname = request.getParameter("newname");
		String newpass = request.getParameter("newpass");
		String kengen = request.getParameter("kengen");
		String choose = request.getParameter("choose");
		// UserList作成
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();
		List<User> userList = registerUserLogic.execute();
		 request.setAttribute("userList", userList);
		// 名前のパターンの生成（姓名を入力、間に全角空白）
		// 「[^ -~｡-ﾟ]+」は「全角以上1回以上の繰り返し」
		Pattern namePattern = Pattern.compile("^[^ -~｡-ﾟ]+$");

		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/userRegisterResult.jsp";
		// リクエストパラメータチェック
		String errorMsg = "";
		if (done.equals("新規")) {
			// 入力値不正確認
			if (newid == null || newid.length() == 0) {
				errorMsg += "idが入力されていません<br>";
			} else if (newid.matches("[a-zA-Z]{1,}")) {
				errorMsg += "数字で入力してください";
			}
			if (newname == null || newname.length() == 0) {
				errorMsg += "名前が入力されていません<br>";
			} else if (namePattern.matcher(newname).matches()) {
				errorMsg += "";
			} else {
				errorMsg += "名前が全角で入力されていません<br>";
			}
			if (newpass == null || newpass.length() == 0) {
				errorMsg += "パスが入力されていません<br>";
			} else if (newpass.matches("[a-zA-Z]{1,9}")) {
				errorMsg += "数字で入力してください";
			}

			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			// 入力値をプロパティに設定
			if ((newid.length() != 0 || newname.length() != 0 || newpass.length() != 0) && errorMsg.length() == 0) {
				int authority = 1;
				if (kengen != null) {
					authority = 2;
				}
				int id = Integer.parseInt(newid);
				int pass = Integer.parseInt(newpass);
				User user = new User(id, newname, pass, authority);
				registerUserLogic = new RegisterUserLogic();
				if (registerUserLogic.create(user)) {
					request.setAttribute("newuser", user);
				} else
					errorMsg += "社員番号が既にあります";
				forwardPath = "/WEB-INF/jsp/userRegisterResult.jsp";
			}
		} else if (done.equals("編集")){
			int id = Integer.parseInt(choose);
			User user = new User();
			user.setId(id);
			registerUserLogic = new RegisterUserLogic();
			registerUserLogic.edit(user);
			request.setAttribute("edituser", user);
			forwardPath = "/WEB-INF/jsp/userEdit.jsp";
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
