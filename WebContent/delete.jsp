<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page errorPage="./error.jsp" %>
<%
	String todo_id = (String)session.getAttribute("todo_id");
	String todo_name = (String)session.getAttribute("todo_name");
%>
<html>
    <head>
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <title>削除確認</title>
      <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
    </head>
    <body>
        <h1>削除確認</h1>
        <hr>
        <div align="center">
            <table border="0">
                <form action="./TodoServlet" method="post">
                    <input type="hidden" name="item_id" value="<%=todo_id %>">
                    <tr>
                        <td class="add_field">
                            項目 <span class=""><%= todo_name %></span> を削除します。<br>
                            よろしいですか？
                        </td>
	            </tr>
                    <tr>
                        <td class="add_button">
                            <table border="0">
                                <tr>
                                    <td>
                                        <input type="submit" name="submit" value="作業削除">
                                    </td>
                                    <td>
                                        <input type="button" value="キャンセル" onclick="javascript:history.back()">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </form>
            </table>
         </div>
     </body>
</html>
