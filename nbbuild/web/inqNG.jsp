<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- 検索エラー画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>ＴＯＤＯリスト</title>
		<link href="/todo/css/todo.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>検索エラー</h1>
		<p>ＴＯＤＯリストは存在しません...</p>
		<form>
			<input class="common_button" type="button" onclick="location.href='./list.jsp'" value="空のリスト"/>
		</form>
	</body>
</html>