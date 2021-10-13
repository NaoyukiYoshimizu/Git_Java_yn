<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Users,model.Seach,model.Seach,java.util.List"%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
// セッションスコープに保存されたユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<Seach> findSeachList = (List<Seach>) request.getAttribute("findSeachList");

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アドレス帳</title>
</head>
<body>
	<p>
		<%=loginUser.getName()%>さん、ログイン中 <a href="/WEB-sql/Logout">ログアウト</a>
	</p>
	<br>
	<hr>
	<h1>アドレス帳メイン</h1>
	<%
	if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<h2>一致するデータはありません</h2>
	<%
	}
	%>		
	<%		
	if ((errorMsg == null || errorMsg.length() == 0) && findSeachList != null) {
		if (findSeachList.size() == 0 ) {%>
			<h2>一致するデータはありません</h2><br>
		<p><%=errorMsg%></p>
	<%		
		}
	%>
	<table border="1" style="width: 450px">
		<tr>
			<th bgcolor="silver">id</th>
			<%
			for (Seach Seach : findSeachList) {
			%>
			<td><p><%=Seach.getId()%></p></td>
			<%
			}
			%>
		</tr>
		<tr>
			<th>名前</th>
			<%
			for (Seach Seach : findSeachList) {
			%>
			<td><p><%=Seach.getName()%></p></td>
			<%
			}
			%>
		</tr>
		<tr>
			<th>年</th>
			<%
			for (Seach Seach : findSeachList) {
			%>
			<td><p><%=Seach.getAge()%></p></td>
			<%
			}
			%>
		</tr>
		<tr>
			<th>住所</th>
			<%
			for (Seach Seach : findSeachList) {
			%>
			<td><p><%=Seach.getAddress()%></p></td>
			<%
			}
			%>
		</tr>
	</table>
	<%
	}
	%>
	<hr>
	<br>
	<a href="/WEB-sql/AddressBookIndex">戻る</a>
</body>
</html>