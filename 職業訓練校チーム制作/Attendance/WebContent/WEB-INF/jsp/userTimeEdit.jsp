<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Work,model.User,java.util.List"%>
<%
//リクエストスコープに保存されたリストを取得
User user = (User) request.getAttribute("user");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
//リクエストスコープに保存されたリストを取得
List<Work> selectList = (List<Work>) request.getAttribute("selectList");
//リクエストスコープに保存されたリストを取得
List<Work> allList = (List<Work>) request.getAttribute("allList");
int i = 0;
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

	<jsp:include page="/header.jsp"/>
	
	<h1><%=user.getName()%>さんの勤怠情報編集中</h1>
	<h2>タイムカード</h2>
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
		</div>
	<%
	if(allList.size() > 0){
	%>
	<form action="/Attendance/UserTimeEdit" method="post">
	<input type="hidden" name="user_id" value="<%=user.getId() %>">
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
	<%
	}if(selectList.size() > 0){
	%>
	<table>
		<tr>
			<th>日付</th>
			<th>出勤時間</th>
			<th>退勤時間</th>
			<th>備考</th>
		</tr>

		<%
		for (i = 0;i < selectList.size()-1;i++) {
			if(selectList.get(i).getTime_id() == 0){
			%>
			<tr>
				<td><p><%=selectList.get(i).getWork_day()%></p></td>
				<td><p>    </p></td>
				<td><p>    </p></td>
				<td><p><%=selectList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else if(selectList.get(i).getTime_id() == 1 && selectList.get(i).getWorkout_stamping_time().length() == 0){
			%>
			<tr>
				<td><p><%=selectList.get(i).getWork_day()%></p></td>
				<td><p><%=selectList.get(i).getStamping_time()%></p></td>
				<td><p>    </p></td>
				<td><p><%=selectList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else if(selectList.get(i).getTime_id() == 2 && selectList.get(i).getWorkout_stamping_time().length() == 0){
			%>
			<tr>
				<td><p><%=selectList.get(i).getWork_day()%></p></td>
				<td><p>    </p></td>
				<td><p><%=selectList.get(i).getStamping_time()%></p></td>
				<td><p><%=selectList.get(i).getNote()%></p></td>
			</tr>
			<%
			}else{
			%>
			<tr>
				<td><p><%=selectList.get(i).getWork_day()%></p></td>
				<td><p><%=selectList.get(i).getStamping_time()%></p></td>
				<td><p><%=selectList.get(i).getWorkout_stamping_time()%></p></td>
				<td><p><%=selectList.get(i).getNote()%></p></td>
			</tr>
		<%
			}
		}
		i = selectList.size()-1;
		if(selectList.get(i).getWorkout_time_id() == 2&&selectList.get(i).getTime_id()==1){
		%>	
		<tr>
			<td><p><%=selectList.get(i).getWork_day()%></p></td>
			<td><p><%=selectList.get(i).getStamping_time()%></p></td>
			<td><p><%=selectList.get(i).getWorkout_stamping_time()%></p></td>
			<td><p><%=selectList.get(i).getNote()%></p></td>
		</tr>
		<%	
		}else if(selectList.get(i).getTime_id() == 1){
		%>
		<tr>
			<td><p><%=selectList.get(i).getWork_day()%></p></td>
			<td><p><%=selectList.get(i).getStamping_time()%></p></td>
			<td><p>    </p></td>
			<td><p><%=selectList.get(i).getNote()%></p></td>
		</tr>
		<%
		}else if(selectList.get(i).getTime_id() == 2){
		%>
		<tr>
			<td><p><%=selectList.get(i).getWork_day()%></p></td>
			<td><p>    </p></td>
			<td><p><%=selectList.get(i).getStamping_time()%></p></td>
			<td><p><%=selectList.get(i).getNote()%></p></td>
		</tr>	
		<%
		}
		%>
	</table>

		<table>
			<tr>
				<td>日付</td>
				<td><select name="day">
						<option value="">選択してください</option>
						<%
						for (i = 0; i < selectList.size(); i++) {
							String day = selectList.get(i).getWork_day();
						%>
						<option value="<%=selectList.get(i).getWork_day()%>"><%=selectList.get(i).getWork_day()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<td>出勤</td>
				<td><select name="in_hour">
						<option value="">選択してください</option>
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
				</select> : <select name="in_minute">
						<option value="">選択してください</option>
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option>
				</select>
			</tr>
			<tr>
				<td>退勤</td>
				<td><select name="out_hour">
						<option value="">選択してください</option>
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
				</select> : <select name="out_minute">
						<option value="">選択してください</option>
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option>
				</select></td>
			</tr>
			<tr>
				<td>備考</td>
				<td><input type="text" name="note"> 
				<input type="submit" name="done" value="変更" class="btn-submit3"></td>
			</tr>
		</table>
	</form>
	<%
	}
	%>

	<hr>

	<br>
	<hr>
	<br>
	<br>
	<br>

	
	<jsp:include page="/footer.jsp"/>
</body>
</html>