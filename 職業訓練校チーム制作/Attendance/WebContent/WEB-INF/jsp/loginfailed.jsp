<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
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
  <div class="container">
	<div class="header"> <!--ヘッダー設定-->
  
 <div class="header-left"> 	
<a href="/Attendance/Main" style="text-decoration:none;color:#073985;" >All White</a> <!-- 1028new -->
   	</div>
  	
  
  	<div class="header-right">
  	<div class="att_p">勤怠管理システム</div>
  	</div>
  	<div class="clear"></div>
  	</div>
  </div>
  
  <div class="error">
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
<h1>ログイン失敗</h1>
	<%
		if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%>
	</p>

	<%
		}
	%>
	  </div>
	

	<jsp:include page="/footer.jsp"/>
</body>
</html>