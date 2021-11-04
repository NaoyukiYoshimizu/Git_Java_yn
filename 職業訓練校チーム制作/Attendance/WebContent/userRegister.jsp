<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,java.util.List"%>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<User> userList = (List<User>) request.getAttribute("userList");
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
  
	<div id="wrapper">
		<div id="container">
			<div id="main">
				<h2>管理者 ログイン中</h2>
				<br>
				<h2>新規ユーザー作成</h2>
				<form action="/Attendance/UserRegister" method="post" >
				<table>
					<tr>
						<th>社員番号</th>
						<th>名前</th>
						<th>パス</th>
						<th> 権限 </th>
						<th>      </th>
					</tr>
					<tr>
						<td><input type="text" name="newid"></td>    
						<td><input type="text" name="newname"></td> 
						<td><input type="text" name="newpass"></td>
						<td>あり<input type="radio" name="kengen" value="2"></td>
						<td><input type="submit" name="done" value="新規" class="btn-submit3"></td> 
					</tr>
				</table>
				<br>
				<br>
				
				<h2>ユーザー情報編集</h2>
				<select name="choose">
				
				<%
 				for (User user : userList) {
				%>
					
				<option value="<%=user.getId()%>"><%=user.getName()%></option>
				<%
				} 
				%>
				</select>
					<input type="submit" name="done" value="編集" class="btn-submit3 ">
				<br>
				<br>
				<table>
					<tr>
						<th>社員番号</th>
						<th>名前</th>
						<th>パス</th>
						<th> 権限 </th>
					</tr>
				<%
 				for (int i = 0; i < userList.size(); i++) {
				%>	 
		 			<tr>
						<td><%=userList.get(i).getId() %></td> 
						<td><%=userList.get(i).getName() %></td> 
						<td>　　　　</td> 
						<%if(userList.get(i).getAuthority() == 2){%> 
						<td>あり</td> 
						<% 
						}else{
						 %>	 
						<td>なし</td> 
						<%
						} 
						%>
											
					</tr> 
				<%
				} 
				%>	
				</table>
				</form>
			</div>
		</div>

	<jsp:include page="/footer.jsp"/>
  </body>
</html>