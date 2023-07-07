<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%

//リクエストスコープに保存されたエラーメッセージを取得
User user = (User) request.getAttribute("user");
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
<br>
<br>
		<p>新規登録に成功しました</p><br>
		<h1>ID:<%=user.getId()%></h1><br>
		<h1>パスワード:<%=user.getPass()%></h1><br>
<br>
<a href="/EC-nilu/" >ログインへ戻る</a>
  <footer>
     Copyright &copy; 2023 nilu inc.
  </footer>
</body>
</html>