<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Syouhinn,model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<Syouhinn> syouhinnList = (List<Syouhinn>) request.getAttribute("syouhinnList");
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
  
    <jsp:include page="/header.jsp"/>
<!-- 商品一覧-->
	<h1>商品一覧</h1>
	<%
	if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<h2>メンテナンスが必要です</h2>
	<%
	}
	%>			
	<form action="/EC-nilu/Main" method="post" >
	<table>
		<tr>
		 <td></td><th>商品名</th><th>価格</th><th>詳しく</th>
		</tr>	
			<%
			for (Syouhinn syouhinn : syouhinnList) {
			%>
			
			<tr><td><p> <%=syouhinn.getKanri_id()%></p></td>
			<td><p><%=syouhinn.getGoods()%></p></td>
			<td><span><%=syouhinn.getSelling_price()%>円</span></td>
			<td><p><input type="checkbox" name="detail" value="<%=syouhinn.getKanri_id()%>">  <input type="submit" name="done" value="詳細"></p></td>
			</tr>
			<%
			}
			%>
	</table>
	</form>
 <jsp:include page="/footer.jsp"/>
  </body>
</html>