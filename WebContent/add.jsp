<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="todo.LoginUserBean"%>
<%@ page import="java.util.Calendar" %>
<%@ page errorPage="./error.jsp" %>
<%
LoginUserBean currentUser = (LoginUserBean) request.getSession().getAttribute("user_db");
ArrayList<LoginUserBean> todoUserList = (ArrayList<LoginUserBean>) request.getSession()
.getAttribute("todoUser_db");
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>作業登録</title>
        <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
    </head>
    <body>
        <h1>作業登録</h1>
        <hr>
        <div align="center">
            <table border="0">
                <form action="./TodoServlet" method="post">
                    <tr>
                        <th class="add_field">
                            項目名
                        </th>
                        <td class="add_field">
                            <input type="text" name="name" value="" size="24">
                        </td>
                    </tr>
                    <tr>
                        <th class="add_field">
                            担当者
                        </th>
                        <td class="add_field">
                            <select name="user_id" size="1">
                            <%
                                for(LoginUserBean bean: todoUserList) {
                                    %><option value="<%= bean.getId() %>" <%
                                    if(bean.getId().equals(currentUser.getId())) {
                                        %>selected<%
                                    }
                                    %>><%= bean.getName() %></option><%
                                }
                            %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="add_field">
                            期限
                        </th>
                        <td class="add_field">
                        <%
                            Calendar calendar = Calendar.getInstance();
                        %>
                            <input type="text" name="year" value="<%= calendar.get(Calendar.YEAR) %>" size="8">/<input type="text" name="month" value="<%= calendar.get(Calendar.MONTH) + 1 %>" size="4">/<input type="text" name="day" value="<%= calendar.get(Calendar.DAY_OF_MONTH) %>" size="4">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="add_button">
                            <table border="0">
                                <tr>
                                    <td>
                                        <input type="submit" name="submit" value="登録">
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