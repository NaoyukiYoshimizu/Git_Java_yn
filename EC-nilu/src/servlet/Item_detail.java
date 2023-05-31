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

import model.Syouhinn;
import model.SyouhinnLogic;
import model.User;

/**
 * Servlet implementation class Item_detail
 */
@WebServlet("/Item_detail")
public class Item_detail extends HttpServlet {
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

				// リクエストパラメータチェック
				if (done.equals("")) {
					// SyouhinnList作成
					
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
