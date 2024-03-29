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
		String errorMsg = "";
		if (loginUser == null) { // ログインしていない
			// リダイレクト
			response.sendRedirect("/EC-nilu/");
		} else { // ログイン済み
			// SyouhinnList作成
			SyouhinnLogic syouhinnLogic = new SyouhinnLogic();
			List<Syouhinn> syouhinnList = syouhinnLogic.execute();
			request.setAttribute("syouhinnList", syouhinnList);
			//エラーを空白に
			request.setAttribute("errorMsg", errorMsg);
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
		//数字かどうかの判定
		  boolean result = false;

		  //一文字ずつ先頭から確認する。for文は文字数分繰り返す
		  for(int i = 0; i < done.length(); i++) {
		    //i文字めの文字についてCharacter.isDigitメソッドで判定する
		    if(Character.isDigit(done.charAt(i))) {
		      //数字の場合は次の文字の判定へ
		    	result =true;
		      continue;
		    }else {

		      //数字でない場合は検証結果をfalseに上書きする
		      result =false;
		      break;
		    }
		  }
		  
		long user_id = (loginUser.getId());
		long kanri_id = 0;
		try {	
			//kanri_id = Long.parseLong(detail);
		}catch(NumberFormatException e) {
			//detail = "0";
		}

		// リクエストパラメータチェック
		if (done.equals("マイページ")) {
			// mypage作成
			loginUser.setId(user_id);
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			registerUserLogic.edit(loginUser);
			request.setAttribute("userinfo", loginUser);
			forwardPath = "/WEB-INF/jsp/mypage.jsp";
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
		}else if (result = true) {
			kanri_id = Long.parseLong(done);
			syouhinn.setKanri_id(kanri_id);
			syouhinnLogic.detail(syouhinn);
			try {
				Thread.sleep(1000); // 1秒止める
				System.out.println(syouhinn.getGoods());
			} catch (InterruptedException e) {
			}
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
