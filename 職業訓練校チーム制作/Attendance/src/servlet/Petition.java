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
import model.User;
import model.Work;

/**
 * Servlet implementation class Petition
 */
@WebServlet("/Petition")
public class Petition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/Attendance/");
		} else { // ログイン済み
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/petition.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");
		String day = request.getParameter("day");
		String msg = request.getParameter("message");
		String errorMsg = "";
		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/petitionResult.jsp";
		// idセット
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		int user_id = (loginUser.getId());
		Work work = new Work(user_id);
		// リクエストパラメータチェック
		if (msg == null || msg.length() == 0) {
			errorMsg += "空白です<br>";
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);

		if (errorMsg.length() == 0 && msg.length() != 0) {
			if (done.equals("送 信")) {
				work.setWork_day(day);
				work.setNote(msg);
				PostWorkLogic postWorkLogic = new PostWorkLogic();
				postWorkLogic.update(work);
				request.setAttribute("result", work);
				// フォワード
				RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
				dis.forward(request, response);
			}
		} else {
			// allList作成
			GetWorkLogic getWorkLogic = new GetWorkLogic();
			List<Work> allList = getWorkLogic.findAll(work);
			request.setAttribute("allList", allList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/petition.jsp");
			dispatcher.forward(request, response);

		}
	}

}
