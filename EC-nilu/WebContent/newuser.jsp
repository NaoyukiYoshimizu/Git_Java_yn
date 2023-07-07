<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
	<meta charset="UTF-8">
	<title>EC-nilu</title>
	<link rel="stylesheet" href="css/style.css">
  </head>
  
  <body>
  <div class="container">
  <br>
  
  <h1>新規登録</h1><br>
		<form action="/EC-nilu/Newuser" method="post"> <!--パス確認-->
		 	<p>名前:  姓<input type="text" name="sei" required>　名<input type="text" name="meii" required></p><br>
			<p>パスワード:<input type="password" name="pass" required></p><br>
			パスワードは半角英数6～9文字で入力してください<br>
			<p>パスワード再入力:<input type="password" name="pass2" required></p><br>
			<p>メール:<input type="email" name="mail" required></p><br>
			<p>住所:<input type="text" name="address" required></p><br><br>
			<!-- 新規登録 -->	
			<input type="submit" name="done" value="登録" class="basic-button">
 	</form>
 	<%
	if (errorMsg.length() != 0) {
	%>
	<div class="error"><p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<%
	}
	%>	
	</div>
	</div>
  <footer>
     Copyright &copy; 2023 nilu inc.
   </footer>
  </body>
</html>