<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
User user = (User) request.getAttribute("edituser");
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
	<br>
	<br>
	<h2 style="text-align:center;">ユーザー編集</h2>
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

	<form action="/Attendance/UserEdit" method="post" >
		<input type="hidden" name="userid" value="<%=user.getId()%>">
	<table border="1" style="margin: auto">
		<tr>
			<td><!-- <input type="checkbox" name="choose" value="0"> -->id</td>
			<td><%=user.getId()%></td>
			<!--  <td><input type="text" name="newid"></td>	-->
		</tr>
		<tr>
			<td><!-- <input type="checkbox" name="choose" value="1"> -->名前</td>
			<td><%=user.getName()%></td>
			<!--  <td><input type="text" name="newname"></td>	-->
		</tr>	
		<tr>
			<td><!-- <input type="checkbox" name="choose" value="2"> -->パス</td>
			<td><%=user.getPass()%></td>
			<!-- <td><input type="text" name="newpass"></td>	-->
		</tr>
		<tr>
			<td><!-- <input type="checkbox" name="choose" value="3"> -->権限</td>
			<%if(user.getAuthority() == 2){%>
			<td>あり</td>
			<%
			}else{
			%>
			<td>なし</td>
			<%
			}
			%>
			<!-- <td>権限あり<input type="radio" name="kengen" value="2">　権限なし<input type="radio" name="kengen" value="1"></td>	-->
		</tr>
	</table>
	<br>
	<br>
	<p style="text-align:center;">編集内容</p>
	<p style="text-align:center;">
	  <select name="choose">
			<option value="name">名前</option>
			<option value="pass">パスワード</option>
			<option value="kengen">権限</option>
	  </select>
	<br>
	<input type="text" name="after">
	<br>
	<br>
	<input type="submit" name="done" value="変更" class="btn-submit3">
	</form>> 
	</p>
	</form>
	<br>
	<br>

<jsp:include page="/footer.jsp"/>
</body>
</html>