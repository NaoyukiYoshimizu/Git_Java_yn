<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Users"%>
<%
// セッションスコープからユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アドレス帳</title>
</head>
</head>
<body>
	<h1>アドレス帳ログイン</h1>
	<%
		if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%>
	</p>
		<a href="/WEB-sql/">戻る</a>
	<%
		}
	%>
	<%
		if (errorMsg == null || errorMsg.length() == 0) {
	%>
	<p>ログインに成功しました</p>
	<p>
		ようこそ<%=loginUser.getName()%>さん
	</p>
	<a href="/WEB-sql/AddressBookIndex">アドレス帳閲覧へ</a>
	<%
	}
	%>
</body>
</html>