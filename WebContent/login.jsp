<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page errorPage="./error.jsp" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>ログイン</title>
        <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
        <script type="text/javascript" src="./login.js">
        </script>
    </head>
    <body onload="fieldChanged();">
        <h1>ログイン</h1>
        <hr>
        <div align="center">
            <table border="0">
                <form action="./LoginServlet" method="post">
                    <tr>
                        <th class="login_field">
                            ユーザID
                        </th>
                        <td class="login_field">
                            <input type="text" name="user_id" value=""
							size="24" id="user_id"
							onkeyup="fieldChanged();"
							onchange="fieldChanged();">
                        </td>
                    </tr>
                    <tr>
                        <th class="login_field">
                            パスワード
                        </th>
                        <td class="login_field">
                            <input type="password" name="password" value=""
							size="24" id="password"
							onkeyup="fieldChanged();"
							onchange="fieldChanged();">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="login_button">
                            <input type="submit" value="ログイン" name="submit" id="login">
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>