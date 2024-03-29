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

import dao.UserDAO;
import model.LoginLogic;
import model.LoginUser;
import model.Syouhinn;
import model.SyouhinnLogic;
import model.User;

@WebServlet("/Login")
public class Login  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("pass");
		String done = request.getParameter("done");
		// パスワードのパターンの生成（半角英数6文字以上9文字以下）
		Pattern passPattern = Pattern.compile("^[0-9a-zA-Z]{6,9}$");

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
			errorMsg += "パスワードが半角英数6～9文字で入力されていません<br>";
		}
		// フォワード先を決定する変数urlを定義
		String forwardPath = "null";
		if (errorMsg.length() != 0 && (user_id.length() != 0 && password.length() != 0 && done.equals("ログイン"))) {
			// LoginUserインスタンスの生成
			long id = Long.parseLong(user_id);
			LoginUser loginUser = new LoginUser(id, password);

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
				errorMsg = "";
				request.setAttribute("errorMsg", errorMsg);// フォワード先をmain.jspに選択
				//商品一覧作成
				SyouhinnLogic syouhinnLogic = new SyouhinnLogic();
				List<Syouhinn> syouhinnList = syouhinnLogic.execute();
				System.out.println(syouhinnList.get(2).getKanri_id());
				request.setAttribute("syouhinnList", syouhinnList);
				forwardPath = "/WEB-INF/jsp/main.jsp";

			} else {// ログイン失敗時の処理
				errorMsg = "ユーザー名またはパスワードが違います<br>";
				// エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", errorMsg);
				// フォワード先をloginfailed.jspに選択
				forwardPath = "/WEB-INF/jsp/loginfailed.jsp";
			}
		}else if(done.equals("新規登録")){
			errorMsg = "";
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			// フォワード先
			forwardPath = "/newuser.jsp";
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
