<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Syouhinn,model.User,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
List<Syouhinn> incartList = (List<Syouhinn>) request.getAttribute("incartList");
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
	<%
	if (incartList.size() ==0) {
	%>
	<br>
	<h2>カートは空です</h2>
	<%
	}else{
	%>			
	<form action="/EC-nilu/Incart" method="post" >
	<table>
		<tr>
		 <td></td><th>商品名</th><th>価格</th><td></td>
		</tr>	
			<%
			for (Syouhinn incart : incartList) {
			%>
			<tr><td><p></p></td>
			<td><p><%=incart.getGoods()%></p></td>
			<td><h2><%=incart.getSelling_price()%></h2>円</td>
			<td><input type="radio" name="delgoods" value="<%=incart.getKanri_id()%>">
			<p class="buttons"><input type="submit" name="done" class="submit" id="submit" value="削除" ></p></td>
			</tr>

			<%
			p_total += incart.getSelling_price();
			}
			%>
	</table>
	<br>
	<h2>合計  <%=p_total%>  円</h2>
	<br>
	<input type="submit" name="done" value="購入手続き" class="btn-submit3">
	</form>
	<%
	}
	%>
  </div>

    <jsp:include page="/footer.jsp"/>

  </body>
</html>