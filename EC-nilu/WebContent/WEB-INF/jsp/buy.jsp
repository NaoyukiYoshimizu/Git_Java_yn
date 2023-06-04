<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Syouhinn,model.User,java.util.List" %>
<%@ page import="java.time.*,java.time.format.*" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたリストを取得
User user = (User) request.getAttribute("userinfo");
List<Syouhinn> incartList = (List<Syouhinn>) request.getAttribute("incartList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");

int p_total=0;
LocalDate ld = LocalDate.now();
ld = ld.plusDays(2);
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
	<h1>　注文内容</h1>
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
	<h2>カートは空です</h2>
	<%
	}
	%>			
	<form action="/EC-nilu/IncartResult" method="post" >
	<%
	for (Syouhinn incart : incartList) {
		p_total += incart.getSelling_price();
	}
	%>
	お届け先<%=user.getAddres()%><br>
	お届け日時<input type="date" name="d-date" value="<%=ld%>" min="<%=ld%>">
	<br><hr>
	<br>
	商品合計　　　<p><%=p_total%>円</p><br>
	送料　　　　　<p>    350円</p><br>
	<br>
	総合計　　　　<h2><%=p_total+350%></h2>円
	<br>
	<input type="submit" name="done" value="注文を確定" class="btn-submit3">
	</form>
	<br>
	<br>
	<hr>
	お支払方法　　<select name="pay" >
			<option value="cre">クレジット一括</option>
			<option value="dai" selected>代引き</option>
			<option value="dai">コンビニ支払い</option>
		</select>
	<br>
	<br>
	<br>
	<hr>
	<table>
		<tr>
		 <td></td><th>商品名</th><th>価格</th>
		</tr>	
			<%
			for (Syouhinn incart : incartList) {
			%>
			<tr><td><p> </p></td>
			<td><p><%=incart.getGoods()%></p></td>
			<td><p><%=incart.getSelling_price()%>円</p></td>
			<%
			p_total += incart.getSelling_price();
			}
			%>
	</table>
	<br>
  </div>
  <jsp:include page="/footer.jsp"/>
  </body>
</html>