<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Work,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
Work work = (Work) request.getAttribute("result");
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
  
   <jsp:include page="/header.jsp"/>
   
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
   <div class="input_na_pa"> <!--中央配置、文字色-->
	申請者　　<%=loginUser.getName()%><br>
		申請対象の日付<br>
		<%= work.getWork_day()%>
		<br>
    	申請内容<br>
		<%= work.getNote()%><br>
  	 	<br>
  	 	以上の内容で送信しました。

   </div>

<jsp:include page="/footer.jsp"/>
  </body>
</html>