<%@ page import="servret.Sqlsample00" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Users" %>
<%
// セッションスコープに保存されたユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
%>
<%
Sqlsample00 sqlsam = new Sqlsample00();
int i=0;
sqlsam.main();
int[]  age = sqlsam.setAge();
String[]  name = sqlsam.setName();
String[]  address = sqlsam.setAddress();

System.out.println("以下のデータが送られました");
while (i<name.length) {
	System.out.println(name[i] + "|" + age[i]+ "|" +address[i]);
	i++;
	if(name[i]==null)break;
}
%>
    
<!DOCTYPE html>
<html lang ="ja">
<head>
<meta charset="UTF-8">
<title>アドレス帳</title>
</head>
<body>
<h1>アドレス帳</h1>
<table border="1" style="width:450px">
<tr><th>名前</th>
<%  i=0;
	while (i<name.length) {
%>
<td><p><%= name[i] %></p></td> 
<%  i++; 
    if(name[i]==null)break;%>

<%			}%>
</tr>
<tr><th>年</th>
<% 	i=0;
	while (i<name.length) {
%>
<td><p><%=  age[i] %></p></td>
<%  i++; 
    if(name[i]==null)break;%>

<%			}%>
</tr>
<tr><th>住所</th>
<% 	i=0;
	while (i<name.length) {
%>
<td><p><%= address[i] %></p></td>
<%  i++; 
    if(name[i]==null)break;%>

<%			}%>
</tr>
</table>
<form action="/WEB-sql/AddressBookIndex" method="post">
<input type="text" name="text">
<select name="operator">
			<option value="id">id</option>
			<option value="name">name</option>
			<option value="address">address</option>
			<option value="age">age</option>
		</select>
<input type="submit" value="検索">
</form>
<hr>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="/WEB-sql/Logout">ログアウト</a>
</p>
</body>
</html>