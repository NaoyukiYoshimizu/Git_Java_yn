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

import model.RegisterUserLogic;
import model.User;

@WebServlet("/Personedit")
public class Personedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/EC-nilu/");
		} else { // ログイン済み
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");
		String errorMsg = "";
		String after= request.getParameter("after");
		String choose= request.getParameter("choose");
		String sub= request.getParameter("sub");
		// パスワードのパターンの生成（半角英数6文字以上9文字以下）
		Pattern passPattern = Pattern.compile("^[0-9a-zA-Z]{6,9}$");
		//クレジット用パラメータ
		if(choose.equals("pay")) {
				after+= request.getParameter("after1");
				after+= request.getParameter("after2");
				after+= request.getParameter("after3");
				after+= request.getParameter("after4");
				after+= request.getParameter("after5");
		}
		
		//インスタンス化
		User user = new User();
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();

		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/personResult.jsp";
		// idセット
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		long user_id = (loginUser.getId());

		// リクエストパラメータチェック
		if (done.equals("決定")) {
			user.setChoose(choose);
			if(user.getChoose().equals("pass")) {
				if (!after.equals(sub)) {
					errorMsg = "パスワードが一致していません<br>";
				} else if (passPattern.matcher(after).matches()) {
					errorMsg += "";
					// 変更する
					user.setId(user_id);
					user.setAfter(after);
					registerUserLogic.update(user);
				} else {
					errorMsg += "パスワードが半角英数6～9文字で入力されていません<br>";
				}
			}else {
				// 変更する
				user.setId(user_id);
				registerUserLogic.update(user);
			}
		} 
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// 変更情報を表示
		registerUserLogic.edit(user);
		request.setAttribute("user", user);
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
		dis.forward(request, response);
	}

}
