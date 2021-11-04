<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
User user = (User) request.getAttribute("newuser");
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
	<%
	if (errorMsg == null || errorMsg.length() == 0) {
	%>
		<p>ユーザー登録に成功しました</p>
	<table>
		<tr>
			<td>id</td>
			<td><%=user.getId()%></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><%=user.getName()%></td>
		</tr>	
		<tr>
			<td>パス</td>
			<td><%=user.getPass()%></td>
		</tr>
		<tr>
			<td>権限</td>
			<%if(user.getAuthority() == 2){%>
			<td>あり</td>
			<%
			}else{
			%>
			<td>なし</td>
			<%
			}
			%>
		</tr>
	</table>
	<%
	}
	%>
	
	
	<jsp:include page="/footer.jsp"/>
</body>
</html>