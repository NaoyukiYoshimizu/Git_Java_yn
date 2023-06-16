<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
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
	
	<jsp:include page="/header.jsp"/>	
	
	<div class="error">
	<%
	if (errorMsg.length() != 0) {
	%>
		<br>
		<br>
		<p>入力エラー</p>
		<p><%=errorMsg%></p>
		<br>

	<%
	}
	%>
	</div>
<br>
<br>
		<p><%=user.getChoose()%>の変更に成功しました</p>
<br>
<a href="/EC-nilu/Main" >homeへ戻る</a>
 <jsp:include page="/footer.jsp"/>
</body>
</html>