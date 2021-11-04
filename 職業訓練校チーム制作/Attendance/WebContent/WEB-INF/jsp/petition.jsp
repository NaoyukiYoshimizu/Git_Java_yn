<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Work,java.util.List"%>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<Work> allList = (List<Work>) request.getAttribute("allList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>All White 勤怠管理システム</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="favicon.ico">
</head>
<body>

	<jsp:include page="/header.jsp" />


	<div class="input_na_pa">
	
	<br>
	<br>
	<br>
	<br>
		<!--中央配置、文字色-->
		申請者<%=loginUser.getName()%><br>
		<form action="/Attendance/Petition" method="post">
			申請対象の日付<br> <select name="day">
				<%
				for (int i = 0; i < allList.size(); i++) {

					String workday = allList.get(i).getWork_day();
				%>	
				<option value="<%=workday%>"><%=workday%></option>
				<%
				}
				%>
			</select> <br> 申請内容<br>
			<textarea name="message" rows="10" cols="20"></textarea>
			<br> 
			<input type="submit" name="done" value="送 信" class="basic-button">
		</form>
	</div>
	<div class="error">
		<%
		if (errorMsg.length() != 0) {
		%>
		<p>入力エラー</p>
		<p><%=errorMsg%></p>
		<br>

		<%
		}
		%>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="/footer.jsp" />
</body>
</html>