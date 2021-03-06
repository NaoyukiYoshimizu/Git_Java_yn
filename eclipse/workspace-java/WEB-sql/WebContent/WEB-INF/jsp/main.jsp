<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Users,model.AdressBook,java.util.List"%>
<%
// セッションスコープに保存されたユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<AdressBook> AdressBookList = (List<AdressBook>) request.getAttribute("AdressBookList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
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
	</p><br>
<hr>
	<h1>アドレス帳メイン</h1>
	<%
	if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<h2>編集しませんでした</h2>
	<%
	}
	%>			
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
		</tr>
	</table>

	<hr>
	<br>
	<form action="/WEB-sql/AddressBookIndex" method="post">
		<input type="text" name="text"> 
		<select name="operator">
			<option value="id">id</option>
			<option value="name">name</option>
			<option value="address">address</option>
			<option value="age">age</option>
		</select> <input type="submit" name="done" value="検索"><br>
		<input type="submit" name="done" value="編集">
	</form>

</body>
</html>