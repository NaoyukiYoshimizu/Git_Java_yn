<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Syouhinn,model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
Syouhinn syouhinn = (Syouhinn) request.getAttribute("syouhinn");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");

int p_total=0;
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
<!-- 商品一覧-->
	<h1>カート一覧</h1>
	<%
	if (errorMsg.length() != 0) {
	%>
	<p>入力エラー</p>
	<p><%=errorMsg%></p><br>
	<h2>メンテナンスが必要です</h2>
	<%
	}
	%>
	
	<form action="/EC-nilu/Item_detail" method="post" >
	<input type="hidden" name="kanri" value="<%=syouhinn.getKanri_id()%>">
	<table>
		<tr>
		 <td></td><th>商品名</th><th>価格</th><td></td>
		</tr>	
			<tr><td><p></p></td>
			<td><p><%=syouhinn.getGoods()%></p></td>
			<td><h2><%=syouhinn.getSelling_price()%></h2>円</td>			
			</tr>
	</table>
	<p><%=syouhinn.getGoods_detail()%></p>
	<br>
	<br>
	<input type="submit" name="done" value="カートに入れる" class="basic-button2">
	</form>
	<br>
	<br>
  </div>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>