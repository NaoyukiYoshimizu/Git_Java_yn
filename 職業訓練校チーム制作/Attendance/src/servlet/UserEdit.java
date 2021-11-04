package servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		// リクエストパラメータチェック
		String errorMsg = "";
		request.setAttribute("errorMsg", errorMsg);
		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/Attendance/");
		} else { // ログイン済み
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String newid = request.getParameter("newid");
		//String newname = request.getParameter("newname");
		//String newpass = request.getParameter("newpass");
		//String kengen = request.getParameter("kengen");
		String choose = request.getParameter("choose");
		String after = request.getParameter("after");
		String userid = request.getParameter("userid");
		int id = Integer.parseInt(userid);

		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/userEditResult.jsp";
		// リクエストパラメータチェック
		String errorMsg = "";
		// 入力値不正確認
		if (choose != null) {
			if (after == null || after.length() == 0) {
				errorMsg += "idが空白です<br>";
			}
			// UserList作成
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// 入力値不正確認
			if (after.matches("[0-9]{1,}") && (choose.equals("name")|| choose.equals("kengen"))) {
				errorMsg += "文字で入力してください";
			}if (after.matches("[a-zA-Z]{1,}") && (choose.equals("id") || choose.equals("pass"))) {
				errorMsg += "数字で入力してください";
			}else if (after.matches("[a-zA-Z]{1,3}") && choose.equals("pass")) {
				errorMsg += "４文字以上の数字で入力してください";

			}if (choose.equals("id") && after.length() != 0) {
				int afterint = Integer.parseInt(after);
				if (afterint < 0) {
					errorMsg += "正の数で入力してください";
				}
				for (User user : userList) {
					afterint = Integer.parseInt(after);
					if (user.getId() == afterint) {
						errorMsg += "既にある数字です";
						break;
					}
				}
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			User user = new User();
			user.setId(id);
			registerUserLogic = new RegisterUserLogic();
			registerUserLogic.edit(user);
			request.setAttribute("edituser", user);
			if (errorMsg.length() == 0 && after.length() != 0) {
				// 入力値をプロパティに設定
				user = new User(id, choose, after);
				registerUserLogic = new RegisterUserLogic();
				registerUserLogic.update(user);
				if(choose.equals("id")) {
					user = new User();
					int afterint = Integer.parseInt(after);
					user.setId(afterint);
					registerUserLogic = new RegisterUserLogic();
					registerUserLogic.edit(user);
				}else {
					registerUserLogic.edit(user);
				}
				request.setAttribute("edituser", user);	
				
			}else {
				forwardPath = "/WEB-INF/jsp/userEdit.jsp";		}
		} else {
			errorMsg += "変更する項目にチェックしてください";
			forwardPath = "/WEB-INF/jsp/userEdit.jsp";
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		
	}

}
