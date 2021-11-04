<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
	<meta charset="UTF-8">
	<title>All  White  勤怠管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="icon" href="favicon.ico">
  </head>
  
  <body>
  
    <jsp:include page="/header.jsp"/>
  
	<div class="main-visual"><!--イメージ写真設定-->
	</div>
	
<!-- 時計、更新版１-->
<br><p class="nowtime">現在の時刻<br><span id="nowTime"></span></p>
<script src="js/script.js"></script>

<form action="/Attendance/Main" method="post"> <!-- 出勤、退勤、申請、修正（管理者権限のみ） -->
	<p style="text-align:center"><br>
	<input type="submit" name="done" value="出勤" class="basic-button">
	<input type="submit" name="done" value="退勤" class="basic-button">
	
	
</form>

	<jsp:include page="/footer.jsp"/>
  </body>
</html>