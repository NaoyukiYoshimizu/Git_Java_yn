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
	<title>EC-nilu</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="icon" href="favicon.ico">
  </head>
  
  <body>
  <div class="container">
	<div class="header"> <!--ヘッダー設定-->
  
 <div class="header-left"> 	
<a href="/EC-nilu/Main" style="text-decoration:none;color:#073985;" >EC-nilu</a> <!-- 1028new -->
   	</div>
  	
  
  	<div class="header-right">
  	<div class="att_p">EC-nilu</div>
  	</div>
  	<div class="clear"></div>
  	</div>
  </div>
  
  <div class="error">
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
　<br>
  <br>
	<a href="/EC-nilu/" >ログイン画面へ戻る</a>
	  </div>
</body>
</html>