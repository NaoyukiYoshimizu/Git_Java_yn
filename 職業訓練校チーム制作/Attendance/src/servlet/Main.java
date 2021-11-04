package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/Main")
public class Main extends HttpServlet {
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

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");
		String inoutflag = "";
		String errorMsg = "";
		// 現在時刻設定
		String nowtime;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/timesheet.jsp";
		// idセット
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		int user_id = (loginUser.getId());
		Work work = new Work(user_id);

		// リクエストパラメータチェック
		if (done.equals("マイページ")) {
			// WorkList作成
			GetWorkLogic getWorkLogic = new GetWorkLogic();
			List<Work> atdList = getWorkLogic.execute(work);
			if(atdList.size() == -1 || atdList.size() == 0) {
				errorMsg = "データはありません";
			}
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
		} else if (done.equals("出勤")) {
			// 入力値をプロパティに設定
			inoutflag = "work_in";
			// Work Work = new Work();
			nowtime = sdf.format(d);
			work.setInoutflag(inoutflag);
			work.setStamping_time(nowtime);
			PostWorkLogic postWorkLogic = new PostWorkLogic();
			if (!postWorkLogic.create(work)) {
				errorMsg = "sqlエラーもしくは2回目の打刻です";
			}
		} else if (done.equals("退勤")) {
			// 入力値をプロパティに設定
			inoutflag = "work_out";
			// Work Work = new Work();
			nowtime = sdf.format(d);
			work.setInoutflag(inoutflag);
			work.setStamping_time(nowtime);
			PostWorkLogic postWorkLogic = new PostWorkLogic();
			if (!postWorkLogic.create(work)) {
				errorMsg = "sqlエラーもしくは2回目の打刻です";
			}

		}else if (done.equals("出退勤")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/main.jsp";
			
		}else if (done.equals("申請")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/petition.jsp";
			
		} else if (done.equals("ユーザー管理")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/userRegister.jsp";
			
		}else if (done.equals("勤怠管理")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/userTimeEditSelect.jsp";
			
		}else if (done.equals("ログアウト")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/logout.jsp";
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// WorkList作成
		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work> atdList = getWorkLogic.execute(work);
		request.setAttribute("AtdList", atdList);
		// allList作成
		getWorkLogic = new GetWorkLogic();
		List<Work> allList = getWorkLogic.findAll(work);
		request.setAttribute("allList", allList);
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
		dis.forward(request, response);

	}
}
