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

@WebServlet("/TimeMonthSelect")
public class TimeMonthSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		int user_id = (loginUser.getId());
		Work work = new Work(user_id);
		// allList作成
		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work> allList = getWorkLogic.findAll(work);
		request.setAttribute("allList", allList);
		// selectList作成
		PostWorkLogic postWorkLogic = new PostWorkLogic();
		List<Work> selectList = postWorkLogic.monthexecute(work);
		request.setAttribute("selectList", selectList);
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/timesheetSelect.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String month = request.getParameter("month");
		String done = request.getParameter("done");
		String errorMsg = "";
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		int user_id = (loginUser.getId());
		if(done.equals("検索")) {
		Work work = new Work(user_id);
		work.setWork_day(month);
		// WorkList作成
		GetWorkLogic getWorkLogic = new GetWorkLogic();
		List<Work>allList = getWorkLogic.findAll(work);
		request.setAttribute("allList", allList);
		// selectList作成
		PostWorkLogic postWorkLogic = new PostWorkLogic();
		List<Work> selectList = postWorkLogic.monthexecute(work);
		request.setAttribute("selectList", selectList);
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/timesheetSelect.jsp");
		dispatcher.forward(request, response);
		}
	}

}
