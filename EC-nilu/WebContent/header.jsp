<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
    
  <div class="container">
	<div class="header"> <!--ヘッダー設定-->
      
  	<div class="header-left"> 	
<a href="/EC-nilu/Main" style="text-decoration:none;color:#073985;" >EC-nilu</a> <!-- 1028new -->
   	</div>
  	
  
  	<div class="header-right">
  	<div class="att_p">HOME</div>
  	</div>
  	<div class="clear"></div>
  	</div>
  </div>
  
<div class="head-c">
   <form action="/EC-nilu/Main" method="post">
    <%if(loginUser.getAuthority() == 1){ 
	%>
   	<input type="submit" name="done" value="カート" class="head-button">
   	<%
	}
	%>
	<input type="submit" name="done" value="マイページ" class="head-button">
	<%if(loginUser.getAuthority() == 2){ 
	%>
	<input type="submit" name="done" value="ユーザー管理" class="head-button">
	<input type="submit" name="done" value="在庫管理" class="head-button">
	<%
	}
	%>
	</form>
</div>
  