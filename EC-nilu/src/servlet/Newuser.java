package servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class Newuser
 */
@WebServlet("/Newuser")
public class Newuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String sei = request.getParameter("sei");
				String mei = request.getParameter("mei");
				String password = request.getParameter("pass");
				String pass2 = request.getParameter("pass2");
				String mail = request.getParameter("mail");
				String address = request.getParameter("address");
				
				// パスワードのパターンの生成（半角英数6文字以上9文字以下）
				Pattern passPattern = Pattern.compile("^[0-9a-zA-Z]{6,9}$");

				// リクエストパラメータをチェック
				String errorMsg = "";
				if (passPattern.matcher(password).matches()) {
					errorMsg += "";
				} else {
					errorMsg += "パスワードが半角英数6～9文字で入力されていません<br>";
				}
				// フォワード先を決定する変数urlを定義
				String forwardPath = "null";
				if (errorMsg.length() != 0 && password.equals(pass2)) {
					String username = sei +"　"+ mei;
					User user = new User();
					user.setName(username);
					user.setPass(password);
					user.setMail(mail);
					user.setAddres(address);
					RegisterUserLogic registerUserLogic = new RegisterUserLogic();
					registerUserLogic.create(user);
					request.setAttribute("user", user);
					// フォワード先をloginfailed.jspに選択
					forwardPath = "/WEB-INF/jsp/newuserResult.jsp";
				}else {// ログイン失敗時の処理
					errorMsg += "パスワードが一致していません<br>";
					// エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", errorMsg);
					// フォワード先をloginfailed.jspに選択
					forwardPath = "/newuser.jsp";
				}		
				// ログイン結果の画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);
	}

}
