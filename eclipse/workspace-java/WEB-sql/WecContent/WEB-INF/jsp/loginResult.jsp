<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Users" %>
<%
// セッションスコープからユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アドレス帳</title>
</head>
</head>
<body>
<h1>アドレス帳ログイン</h1>
<% if(loginUser != null) { %>
<p>ログインに成功しました</p>
<p>ようこそ<%= loginUser.getName() %>さん</p>
<a href="/WEB-sql/AddressBookIndex">アドレス帳閲覧へ</a>
<% } else { %>
<p>ログインに失敗しました</p>
<a href="/WEB-sql/">TOPへ</a>
<% } %>
</body>
</html>