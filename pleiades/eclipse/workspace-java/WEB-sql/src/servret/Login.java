package servret;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.Users;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// ログインIDのパターンの生成（半角英数4文字以上）
		Pattern namePattern = Pattern.compile("^[0-9a-zA-Z]{4,}$");
		// パスワードのパターンの生成（半角英数4文字以上8文字以下）
		Pattern passPattern = Pattern.compile("^[0-9a-zA-Z]{4,8}$");

		// リクエストパラメータをチェック
		String errorMsg = "";
		if (name == null || name.length() == 0) {
			errorMsg += "IDが入力されていません<br>";
		} else if (namePattern.matcher(name).matches()) {
			errorMsg += "";
		} else {
			errorMsg += "IDが半角英数4文字以上で入力されていません<br>";
		}

		if (pass == null || pass.length() == 0) {
			errorMsg += "パスワードが入力されていません<br>";
		} else if (passPattern.matcher(pass).matches()) {
			errorMsg += "";
		} else {
			errorMsg += "パスワードが半角英数4～8文字で入力されていません<br>";
		}

		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		/* ここまで追加機能 */

		// Usersインスタンス（ユーザー情報）の生成
		Users users = new Users(name, pass);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(users);

		// ログイン成功時の処理
		if (isLogin) {
			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", users);
		}else {
			errorMsg += "ユーザー名またはパスワードが違います<br>";
			request.setAttribute("errorMsg", errorMsg);
		}
		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}
}