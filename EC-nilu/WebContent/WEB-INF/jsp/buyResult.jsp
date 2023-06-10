<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Delivery,model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたメッセージを取得
Delivery delivery = (Delivery) request.getAttribute("delivery");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
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
  <div class="container">
    <jsp:include page="/header.jsp"/>
    
    お買い上げありがとうございます。
    お届け日は<h1><%=delivery.getDelivery()%></h1>です
    
  </div>

    <jsp:include page="/footer.jsp"/>

  </body>
</html>