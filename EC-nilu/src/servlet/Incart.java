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

@WebServlet("/Incart")
public class Incart extends HttpServlet {
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
				String errorMsg = null;
				String delgoods = request.getParameter("delgoods");
				//インスタンス化
				Syouhinn syouhinn = new Syouhinn();
				SyouhinnLogic syouhinnLogic = new SyouhinnLogic();
				RegisterUserLogic registerUserLogic = new RegisterUserLogic();

				// フォワード先を決定する変数urlを定義
				String forwardPath = "/WEB-INF/jsp/incart.jsp";
				// idセット
				// セッションスコープからユーザー情報を取得
				HttpSession session = request.getSession();
				User loginUser = (User) session.getAttribute("loginUser");

				long user_id = (loginUser.getId());
				long kanri_id = 0;
				try {
					System.out.println("変換先"+delgoods);
					kanri_id = Long.parseLong(delgoods);
				}catch(NumberFormatException e) {
					
					delgoods = "0";
					System.out.println("文字列を数値型に変換した際に、数値以外の文字が含まれるなどして変換ができない");
				}

				
				// リクエストパラメータチェック
				if (done.equals("削除")) {
					// 削除
					syouhinn.setKanri_id(kanri_id);
					syouhinnLogic.delete(syouhinn);
					try {
						Thread.sleep(2000); // 2秒止める
					} catch (InterruptedException e) {
					}
					// カートリスト作成
					List<Syouhinn> incartList = syouhinnLogic.incart(syouhinn);
					request.setAttribute("incartList", incartList);
					forwardPath = "/WEB-INF/jsp/incart.jsp";
					errorMsg = "";
				} else if (done.equals("購入手続き")) {
					// 購入リスト作成
					syouhinn.setUser_id(user_id);
					List<Syouhinn> incartList = syouhinnLogic.incart(syouhinn);
					loginUser.setId(user_id);
					registerUserLogic.edit(loginUser);
					request.setAttribute("userinfo", loginUser);
					request.setAttribute("incartList", incartList);
					forwardPath = "/WEB-INF/jsp/buy.jsp";
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
