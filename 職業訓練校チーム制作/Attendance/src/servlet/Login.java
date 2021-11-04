package servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.LoginLogic;
import model.LoginUser;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("pass");
		// パスワードのパターンの生成（半角英数4文字以上8文字以下）
		Pattern passPattern = Pattern.compile("^[0-9a-zA-Z]{4,8}$");

		// リクエストパラメータをチェック
		String errorMsg = null;
		if (user_id == null || user_id.length() == 0) {
			errorMsg = "IDが入力されていません<br>";
		}

		if (password == null || password.length() == 0) {
			errorMsg = "パスワードが入力されていません<br>";
		} else if (passPattern.matcher(password).matches()) {
			errorMsg += "";
		} else {
			errorMsg += "パスワードが半角英数4～8文字で入力されていません<br>";
		}
		// フォワード先を決定する変数urlを定義
		String forwardPath = "null";
		if (errorMsg.length() != 0 && (user_id.length() != 0 && password.length() != 0)) {
			// LoginUserインスタンスの生成
			int id = Integer.parseInt(user_id);
			int pass = Integer.parseInt(password);
			LoginUser loginUser = new LoginUser(id, pass);

			// ログイン処理
			// （モデル：LoginLogicが未完成なので、エラー発生してます！！）
			LoginLogic loginLogic = new LoginLogic();
			boolean isLogin = loginLogic.execute(loginUser);

			// ログイン時の処理
			if (isLogin) {// ログイン成功時の処理
				// ユーザー情報をデータベースからUserDAOを経由して取得し、Userインスタンスに登録。
				UserDAO dao = new UserDAO();
				User user = dao.findByLogin(loginUser);

				// ユ;ーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);

				// フォワード先をmain.jspに選択
				forwardPath = "/WEB-INF/jsp/main.jsp";

			} else {// ログイン失敗時の処理
				errorMsg = "ユーザー名またはパスワードが違います<br>";
				// エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", errorMsg);
				// フォワード先をloginfailed.jspに選択
				forwardPath = "/WEB-INF/jsp/loginfailed.jsp";
			}
		}else {// ログイン失敗時の処理
			errorMsg += "ユーザー名またはパスワードが違います<br>";
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			// フォワード先をloginfailed.jspに選択
			forwardPath = "/WEB-INF/jsp/loginfailed.jsp";
		}

		// ログイン結果の画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
