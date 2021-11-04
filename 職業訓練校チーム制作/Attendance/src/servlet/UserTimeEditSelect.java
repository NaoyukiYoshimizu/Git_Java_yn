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

import model.GetWorkLogic;
import model.PostWorkLogic;
import model.RegisterUserLogic;
import model.User;
import model.Work;

@WebServlet("/UserTimeEditSelect")
public class UserTimeEditSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UserList作成
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();
		List<User> userList = registerUserLogic.execute();
		request.setAttribute("userList", userList);
		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work> atdList = getWorkLogic.execute(null);
		request.setAttribute("atdList", atdList);
		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/Attendance/Login");
		} else { // ログイン済み
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTimeEditSelect.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String strId = request.getParameter("user_id");
		int id = Integer.parseInt(strId);
		String name = request.getParameter("user_name");
		Work work = new Work(id);
		System.out.println(id);
		User user = new User();
		user.setId(id);
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();
		registerUserLogic.edit(user);
		request.setAttribute("user", user);

		// リクエストパラメーターをチェック
		String errorMsg = "";
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// selectList作成
		work.setWork_day("");

		PostWorkLogic postWorkLogic = new PostWorkLogic();
		List<Work> selectList = postWorkLogic.monthexecute(work);
		if (selectList.size() == -1 || selectList.size() == 0) {
			errorMsg = "データはありません";
		}
		request.setAttribute("selectList", selectList);
		// allList作成
		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work> allList = getWorkLogic.findAll(work);
		request.setAttribute("allList", allList);
		work = new Work(id);
		work.setWork_day("");
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/userTimeEdit.jsp";
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}

}
