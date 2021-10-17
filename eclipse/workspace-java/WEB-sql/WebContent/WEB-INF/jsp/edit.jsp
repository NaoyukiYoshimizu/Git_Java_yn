<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Users,model.AdressBook,java.util.List"%>
<%
// セッションスコープに保存されたユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<AdressBook> AdressBookList = (List<AdressBook>) request.getAttribute("AdressBookList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アドレス帳</title>
<link rel="stylesheet" href="css/timesheet.css">
</head>
<body>
	<p>
		<%=loginUser.getName()%>さん、ログイン中 <a href="/WEB-sql/Logout">ログアウト</a>
	</p>
	<br>
	<hr>
	<h1>アドレス帳編集</h1>
	<form action="/WEB-sql/AddressBookEdit" method="post">
		<table>
			<tr>
				<th>id</th>
				<%
				for (AdressBook adressbook : AdressBookList) {
				%>
				<td><p><%=adressbook.getId()%></p></td>
				<%
				}
				%>
				<td><input type="submit" name="done" value="新規作成"></td>
			</tr>
			<tr>
				<th>名前</th>
				<%
				for (AdressBook adressbook : AdressBookList) {
				%>
				<td><p><%=adressbook.getName()%></p></td>
				<%
				}
				%>
				<td><p><input type="text" name="newname"></p></td>
			</tr>
			<tr>
				<th>年</th>
				<%
				for (AdressBook adressbook : AdressBookList) {
				%>
				<td><p><%=adressbook.getAge()%></p></td>
				<%
				}
				%>
				<td><input type="text" name="newage"></td>
			</tr>
			<tr>
				<th>住所</th>
				<%
				for (AdressBook adressbook : AdressBookList) {
				%>
				<td><p><%=adressbook.getAddress()%></p></td>
				<%
				}
				%>
				<td><input type="text" name="newaddress"></td>
			</tr>
		</table>
		<br>
		<br>
		旧<input type="text" name="before"> 新<input type="text"
			name="after"> <select name="choose">
			<option value="id">id</option>
			<option value="name">name</option>
			<option value="address">address</option>
			<option value="age">age</option>
		</select> <input type="submit" name="done" value="更新"><br> 
		<p>更新対象idの指定(更新時は必ず選択してください)</p>
			<%
			for (AdressBook adressbook : AdressBookList) {
			%>
				<input type="radio" name="id" value="<%=adressbook.getId()%>">
				<%=adressbook.getId()%> 
			<%
			}
			%>
		<br>
		<br>
		<hr>
		<br> <input type="text" name="text"> <select
			name="operator">
			<option value="id">id</option>
		</select> <input type="submit" name="done" value="削除">
	</form>
	<hr>
	<br>
	<a href="/WEB-sql/AddressBookIndex">戻る</a>
</body>
</html>