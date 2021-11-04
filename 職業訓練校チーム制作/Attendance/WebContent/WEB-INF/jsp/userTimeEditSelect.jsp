<%@page import="model.Work,model.User,java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
//リクエストスコープに保存されたリストを取得
List<User> userList = (List<User>) request.getAttribute("userList");

%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>All White 勤怠管理システム</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="favicon.ico">
</head>

<body>
	
	<jsp:include page="/header.jsp"/>
	
	<br>
	<br>
	<h2 style="text-align:center;">勤怠管理編集</h2>
	<form action="/Attendance/UserTimeEditSelect" method="post">
		<table border="1" style="margin: auto">

			<tr>
				<th>社員番号</th>
				<th>名前</th>
				<th>編集</th>
			</tr>
			<%
			for (User user : userList) {
			%>
			<tr>
				<td><%=user.getId()%>
				<td><%=user.getName()%>
				<td>
				<input type="hidden" name="user_id" value="<%=user.getId() %>">
				<input type="hidden" name="user_name" value="<%=user.getName() %>">
				<input type="submit" name="done"value="編集" class="btn-submit3">
			</tr>
			<%
			}
			%>

		</table>
	</form>
	
	<jsp:include page="/footer.jsp"/>
</body>
</html>