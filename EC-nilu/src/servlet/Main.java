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
import model.Syouhinn;
import model.SyouhinnLogic;
import model.User;

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
			response.sendRedirect("/EC-nilu/");
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
		String detail = request.getParameter("detail");
		String errorMsg = "";
		//インスタンス化
		Syouhinn syouhinn = new Syouhinn();
		SyouhinnLogic syouhinnLogic = new SyouhinnLogic();

		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/main.jsp";
		// idセット
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		long user_id = (loginUser.getId());
		long kanri_id = 0;
		try {
			System.out.print("detail"+detail);	
			kanri_id = Long.parseLong(detail);
		}catch(NumberFormatException e) {
			detail = "0";
			System.out.println("文字列を数値型に変換した際に、数値以外の文字が含まれるなどして変換ができない");
		}

		// リクエストパラメータチェック
		if (done.equals("マイページ")) {
			// SyouhinnList作成
			errorMsg = "";
		} else if (done.equals("カート")) {
			// カートリスト作成
			syouhinn.setUser_id(user_id);
			List<Syouhinn> incartList = syouhinnLogic.incart(syouhinn);
			request.setAttribute("incartList", incartList);
			forwardPath = "/WEB-INF/jsp/incart.jsp";
			errorMsg = "";
		} else if (done.equals("ユーザー管理")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/userRegister.jsp";
			errorMsg = "";
		}else if (done.equals("在庫管理")) {

			// フォワード
			forwardPath = "/WEB-INF/jsp";
			errorMsg = "";
		}else if (done.equals("詳細")) {
			syouhinn.setKanri_id(kanri_id);
			syouhinnLogic.detail(syouhinn);
			request.setAttribute("syouhinn", syouhinn);
			// フォワード
			forwardPath = "/WEB-INF/jsp/detail.jsp";
			errorMsg = "";
		}else if (done.equals("ログアウト")) {
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			List<User> userList = registerUserLogic.execute();
			request.setAttribute("userList", userList);
			// フォワード
			forwardPath = "/WEB-INF/jsp/logout.jsp";
			errorMsg = "";
		}
		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		// SyouhinnList作成
		List<Syouhinn> syouhinnList = syouhinnLogic.execute();
		request.setAttribute("syouhinnList", syouhinnList);
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
		dis.forward(request, response);

	}
}
