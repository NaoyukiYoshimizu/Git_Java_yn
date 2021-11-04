<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Work,model.User,java.util.List"%>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<Work> atdList = (List<Work>) request.getAttribute("AtdList");
//リクエストスコープに保存されたリストを取得
List<Work> allList = (List<Work>) request.getAttribute("allList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
int i = 0;
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
  
	<h1><%=loginUser.getName()%>さん、ログイン中
	</h1>

	<br>
	<div class="error">
	<%
	if (errorMsg.length() != 0) {
	%>
		<br>
		<br>
		<p>入力エラー</p>
		<p><%=errorMsg%></p>
		<br>

	<%
	}
	%>
	<%
	if(allList.size() > 0){
	%>
	<form action="/Attendance/TimeMonthSelect" method="post">
		<select name="month">
		<%
 		for (i = 0;i < allList.size()-1;i++) {
			int j=i+1;
			String workday = allList.get(i).getWork_day();
			workday = workday.substring(0, 7);
			
			if(allList.get(i+1).getWork_day().matches(workday+".*")){
			}else{%>
			<option value="<%=workday%>"><%=workday%></option>
			<%
			}
		}
		i = allList.size()-1;
		if(i == allList.size()-1){
			String workday = allList.get(i).getWork_day().substring(0, 7);%>
		<option value="<%=workday%>"><%=workday%></option>
		<%	
		}
		%>
		</select>
	<input type="submit" name="done" value="検索" class="basic-button">
	</form>
	<%
	}
	%>
	</div>
	<%
	if (errorMsg.length() == 0&& atdList != null) {
	%>
	
	<table>
		<tr>
			<th>日付</th>
			<th>出勤時間</th>
			<th>退勤時間</th>
			<th>備考</th>
		</tr>

		<%
		for (i = 0;i < atdList.size()-1;i++) {
			if(atdList.get(i).getTime_id() == 0){
			%>
			<tr>
				<td><p><%=atdList.get(i).getWork_day()%></p></td>
				<td><p>    </p></td>
				<td><p>    </p></td>
				<td><p><%=atdList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else if(atdList.get(i).getTime_id() == 1 && atdList.get(i).getWorkout_stamping_time().length() == 0){
			%>
			<tr>
				<td><p><%=atdList.get(i).getWork_day()%></p></td>
				<td><p><%=atdList.get(i).getStamping_time()%></p></td>
				<td><p>    </p></td>
				<td><p><%=atdList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else if(atdList.get(i).getTime_id() == 2 && atdList.get(i).getWorkout_stamping_time().length() == 0){
			%>
			<tr>
				<td><p><%=atdList.get(i).getWork_day()%></p></td>
				<td><p>    </p></td>
				<td><p><%=atdList.get(i).getStamping_time()%></p></td>
				<td><p><%=atdList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else{
			%>
			<tr>
				<td><p><%=atdList.get(i).getWork_day()%></p></td>
				<td><p><%=atdList.get(i).getStamping_time()%></p></td>
				<td><p><%=atdList.get(i).getWorkout_stamping_time()%></p></td>
				<td><p><%=atdList.get(i).getNote()%></p></td>
			</tr>
		<%
			}
		}
		i = atdList.size()-1;
		if(atdList.get(i).getWorkout_time_id() == 2&&atdList.get(i).getTime_id()==1){
		%>	
		<tr>
			<td><p><%=atdList.get(i).getWork_day()%></p></td>
			<td><p><%=atdList.get(i).getStamping_time()%></p></td>
			<td><p><%=atdList.get(i).getWorkout_stamping_time()%></p></td>
			<td><p><%=atdList.get(i).getNote()%></p></td>
		</tr>
		<%	
		}else if(atdList.get(i).getTime_id() == 1){
		%>
		<tr>
			<td><p><%=atdList.get(i).getWork_day()%></p></td>
			<td><p><%=atdList.get(i).getStamping_time()%></p></td>
			<td><p>    </p></td>
			<td><p><%=atdList.get(i).getNote()%></p></td>
		</tr>
		<%
		}else if(atdList.get(i).getTime_id() == 2){
		%>
		<tr>
			<td><p><%=atdList.get(i).getWork_day()%></p></td>
			<td><p>    </p></td>
			<td><p><%=atdList.get(i).getStamping_time()%></p></td>
			<td><p><%=atdList.get(i).getNote()%></p></td>
		</tr>	
		<%
		}
		%>
	</table>
	<%
	}
	%>
	<hr>
	<a href="/Attendance/Main">戻る</a>
	<br>
	<hr>
	<br>
	<br>
	<br>

	<jsp:include page="/footer.jsp"/>
  </body>
</html>