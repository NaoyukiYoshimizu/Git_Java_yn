package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Delivery;
import model.DeliveryLogic;
import model.Syouhinn;
import model.SyouhinnLogic;
import model.User;

@WebServlet("/IncartResult")
public class IncartResult  extends HttpServlet {
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
		String d_date = request.getParameter("d-date");
		String pay = request.getParameter("pay");
		String item = request.getParameter("item");
		String cnt = request.getParameter("cnt");
		System.out.println(item);
		//インスタンス化
		Delivery delivery = new Delivery();
		DeliveryLogic deliveryLogic = new DeliveryLogic();
		Syouhinn syouhinn = new Syouhinn();
		SyouhinnLogic syouhinnLogic = new SyouhinnLogic();

		// フォワード先を決定する変数urlを定義
		String forwardPath = "/WEB-INF/jsp/buyResult.jsp";
		// idセット
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		long user_id = (loginUser.getId());
		long i=0;
		int j=0,k=0;
		boolean bool = false;
		String str="";

		// リクエストパラメータチェック
		if (done.equals("注文を確定")) {
			while(i<Long.parseLong(cnt)) {
					if(i%2==0) {
						k=j+13;
						str= item.substring(j, k);
					}else {
						j=k+13;
						str= item.substring(k, j);
					}
					syouhinn.setNsin(str);
					syouhinnLogic.findbydata(syouhinn);				
					delivery = new Delivery(user_id,syouhinn.getNsin(),d_date,pay);
					bool = deliveryLogic.create(delivery);
					if(bool) {
						syouhinnLogic.update(syouhinn);
					}
					i++;
				try {
					Thread.sleep(300); // 0.3秒止める
					System.out.println("配達日"+delivery.getDelivery());
					System.out.println("stock"+syouhinn.getStock());
				} catch (InterruptedException e) {
				}
			}
		errorMsg = "";

		// データをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		request.setAttribute("delivery", delivery);
		// フォワード
		RequestDispatcher dis = request.getRequestDispatcher(forwardPath);
		dis.forward(request, response);
		}
	}
}