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

@WebServlet("/UserTimeEdit")
public class UserTimeEdit extends HttpServlet {
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
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");
		String month = request.getParameter("month");
		String strId = request.getParameter("user_id");
		int id = Integer.parseInt(strId);
		String day = request.getParameter("day");
		String in_hour = request.getParameter("in_hour");
		String in_minute = request.getParameter("in_minute");
		String out_hour = request.getParameter("out_hour");
		String out_minute = request.getParameter("out_minute");
		String note = request.getParameter("note");

		// リクエストパラメーターをチェック
		String errorMsg = "";
		User user = new User();
		user.setId(id);
		RegisterUserLogic registerUserLogic = new RegisterUserLogic();
		registerUserLogic.edit(user);
		request.setAttribute("user", user);

		Work work = new Work(id);
		System.out.println(id);
		work.setWork_day(day);
		if (day == null || day.length() == 0) {// 勤怠編集画面で日付が選択されていない場合
			errorMsg = "日付を選択してください。";
		} else {// 日付が選択されている場合かつ、
				// 出勤時間の片方のみが入力されている場合
			if (!in_hour.equals("") && in_minute.equals("") || in_hour.equals("") && !in_minute.equals("")) {//
				errorMsg = "適切な出勤時間を入力してください。";
				// 退勤時間の片方のみが入力されている場合
			} else if (!out_hour.equals("") && out_minute.equals("") || out_hour.equals("") && !out_minute.equals("")) {
				errorMsg = "適切な退勤時間を入力してください。";
			} else if (note.length() > 100) {// noteの文字数が100文字以上の場合
				errorMsg = "備考は100文字以内で記述してください。";
			}
			if (errorMsg.length() == 0) {
				if (in_hour.length() != 0 && out_hour.length() == 0) {// 出勤のみ更新する場合
					errorMsg = "";
					work.setWork_day(day);
					in_hour += ":" + in_minute;
					work.setStamping_time(in_hour);
					work.setTime_id(1);
					work.setNote(note);
					PostWorkLogic postWorkLogic = new PostWorkLogic();
					postWorkLogic.timeupdate(work);

				} else if (in_hour.length() == 0 && out_hour.length() != 0) {// 退勤のみ更新する場合
					errorMsg = "";
					work.setWork_day(day);
					out_hour += ":" + out_minute;
					work.setStamping_time(out_hour);
					work.setTime_id(2);
					work.setNote(note);
					PostWorkLogic postWorkLogic = new PostWorkLogic();
					postWorkLogic.timeupdate(work);

				} else if (in_hour.length() != 0 && out_hour.length() != 0) {// 出勤退勤ともに更新する場合
					// 出勤の処理
					errorMsg = "";
					work.setWork_day(day);
					in_hour += ":" + in_minute;
					work.setStamping_time(in_hour);
					work.setTime_id(1);
					work.setNote(note);
					PostWorkLogic postWorkLogic = new PostWorkLogic();
					postWorkLogic.timeupdate(work);

					// 退勤の処理
					out_hour += ":" + out_minute;
					work.setStamping_time(out_hour);
					work.setTime_id(2);
					postWorkLogic.timeupdate(work);
				} else {
					errorMsg = "";
					work.setWork_day(day);
					work.setTime_id(99);
					work.setNote(note);
					PostWorkLogic postWorkLogic = new PostWorkLogic();
					postWorkLogic.timeupdate(work);
				}

			}

		}
		
		if (done.equals("検索")) {
			errorMsg = "";
			work = new Work(id);
			work.setWork_day(month);
			// WorkList作成
			GetWorkLogic getWorkLogic = new GetWorkLogic();
			List<Work> allList = getWorkLogic.findAll(work);
			request.setAttribute("allList", allList);
			// selectList作成
			PostWorkLogic postWorkLogic = new PostWorkLogic();
			List<Work> selectList = postWorkLogic.monthexecute(work);
			if(selectList.size() == -1 || selectList.size() == 0) {
				errorMsg = "データはありません";
			}
			request.setAttribute("selectList", selectList);
		}else {
			work = new Work(id);
			work.setWork_day("");
			// WorkList作成
			GetWorkLogic getWorkLogic = new GetWorkLogic();
			List<Work> allList = getWorkLogic.findAll(work);
			request.setAttribute("allList", allList);
			// selectList作成
			PostWorkLogic postWorkLogic = new PostWorkLogic();
			List<Work> selectList = postWorkLogic.monthexecute(work);
			if(selectList.size() == -1 || selectList.size() == 0) {
				errorMsg = "データはありません";
			}
			request.setAttribute("selectList", selectList);
		}

		// Work(work_day,stamping_time,note,id,time_id,workout_stamping_time,
		// workout_time_id);
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// workのリスト作成

		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work> atdList = getWorkLogic.execute(work);
		request.setAttribute("AtdList", atdList);
		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/userTimeEdit.jsp";

		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
		dis.forward(request, response);
	}

}