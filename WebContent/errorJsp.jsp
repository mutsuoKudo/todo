<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage="true" %>
<html>
    <head>
      <meta http-equiv="content-type" content="text/html; charset=utf-8">
      <title>エラー</title>
      <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
    </head>
    <body>
        <h1>エラー</h1>
        <hr>
        <div align="center">
            <table border="0">
                <form action="./TodoServlet" method="get">
                	<input type="hidden" name="action" value="login">
                    <tr>
                        <td class="add_field">
                            エラーが発生しました。<br>
                            内容: <%= exception.toString() %>
                            内容: <%= exception.printStackTrace() %>
                        </td>
	            </tr>
                    <tr>
                        <td class="add_button">
                            <input type="button" value="戻る" onclick="javascript:history.back()">
                        </td>
                    </tr>
                </form>
            </table>
         </div>
     </body>
</html>