<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- ログインエラー画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>ログインエラー</title>
		<link href="/todo/css/todo.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>ログインエラー</h1>
		<p>入力されたユーザは存在しません...</p>
		<form>
			<input class="common_button" type="button" onclick="location.href='./login.jsp'" value="戻る"/>
		</form>
	</body>
</html>