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
	<h1>　<%=user.getName()%>さんの登録情報を変更します</h1>
	<br>
	<br>
	<form action="/EC-nilu/Personedit" method="post" >
	<input type="hidden" name="choose" value="<%=user.getChoose()%>" >
	<table>
	<%
	if (user.getChoose().equals("pay")) {
	%>
		<tr>
			<th>クレジット番号</th>
			<td><input type="text" pattern="^[0-9]+$" name="after" style="width:50px;height:10px;" maxlength="4" required>
			-<input type="text" pattern="^[0-9]+$" name="after1" style="width:50px;height:10px;" maxlength="4" required>
			-<input type="text" pattern="^[0-9]+$" name="after2" style="width:50px;height:10px;" maxlength="4" required>
			-<input type="text" pattern="^[0-9]+$" name="after3" style="width:50px;height:10px;" maxlength="4" required>
			</td>			
		</tr>
		<tr>
			<th>有効期限</th>
			<td><input type="number" name="after4" style="width:50px;height:10px;" min="23" required>年
			 / <input type="number" name="after5" style="width:50px;height:10px;"min="1" max="12" required>月
			 </td>
		</tr>
	<%
	}else{
	%>
		<tr>
			<th><%=user.getChoose()%></th><td><input type="text" name="after" style="width: 100px; height: 20px;" required></td>
		</tr>
	<%
	}
	if (user.getChoose().equals("pass")) {
	%>		
		<tr>
			<th>もう一度パスワードを入力<br>パスワードは半角英数字6～9文字で入力してますか？</th>
			<td><input type="password" name="sub" style="width: 100px; height: 20px;" required></td>
		</tr>
	<%
	}
	%>
	</table>
	<br>
	<br>
	<input type="submit" name="done" value="決定" class="btn-submit3"><br>
	<br>
	<a href="/EC-nilu/Main" >homeへ戻る</a>
	</form>
	<br>
</div>
  <jsp:include page="/footer.jsp"/>
</body>
</html>