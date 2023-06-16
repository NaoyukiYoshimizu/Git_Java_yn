<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
User user = (User) request.getAttribute("userinfo");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");

%>
<!DOCTYPE html>
<html lang="ja">
  <head>
	<meta charset="UTF-8">
	<title>EC-nilu</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="icon" href="favicon.ico">
  </head>
  
  <body>
  <div class="container">
    <jsp:include page="/header.jsp"/>
	<h1>　マイページ</h1>
	<%
	if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<%
	}
	%>
	<br>
	<br>
	<form action="/EC-nilu/Mypage" method="post" >
	<table>
		<tr >
			<th>名前</th><td><p><%=user.getName()%></p></td>
		</tr>	
		<tr>
			<th>パスワード</th><td><p>●●●●●●</p></td>
		</tr><tr>
			<th>メール</th><td><p><%=user.getMail()%></p></td>
		</tr><tr>
			<th>住所</th><td><p><%=user.getAddres()%></p></td>
		</tr><tr>
			<th>支払い方法</th><td>
			<%if(user.getCredit()==null){ %>
				<p>代引き</p>
			<% } else {%>
				<p>クレジット登録済み</p>
			<% } %>
			</td>
		</tr>
	</table>
	<br><span>
	  <select name="choose">
			<option value="name">名前</option>
			<option value="pass" selected>パスワード</option>
			<option value="mail">メール</option>
			<option value="addres">住所</option>
			<option value="pay">支払い方法にクレジットを追加</option>
	  </select></span>
	<br>
	<br>
	<input type="submit" name="done" value="変更" class="btn-submit3">
	</form>
	<br>
</div>
  <jsp:include page="/footer.jsp"/>
</body>
</html>