<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="todo.TodoItemBean"%>
<%@ page import="todo.LoginUserBean"%>
<%@ page import="java.util.Calendar" %>
<%@ page errorPage="./error.jsp" %>
<%
    LoginUserBean currentUser = (LoginUserBean) request.getSession().getAttribute("user_db");

    TodoItemBean todoItemList = (TodoItemBean) request.getSession()
            .getAttribute("todo_db");

    ArrayList<LoginUserBean> todoUserList = (ArrayList<LoginUserBean>) request.getSession()
            .getAttribute("todoUser_db");
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>作業更新</title>
        <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
    </head>
    <body>
        <h1>作業更新</h1>
        <hr>
        <div align="center">
            <table border="0">
                <form action="./TodoServlet" method="post">
                    <input type="hidden" name="item_id" value="<%= todoItemList.getId()%>">
                    <tr>
                        <th class="add_field">
                            項目名
                        </th>
                        <td class="add_field">
                            <input type="text" name="name" value="<%= todoItemList.getName()%>" size="24">
                        </td>
                    </tr>
                    <tr>
                        <th class="add_field">
                            担当者
                        </th>
                        <td class="add_field">
                            <select name="user_id" size="1">
                                <%
                                    for (LoginUserBean bean : todoUserList) {
                                %><option value="<%= bean.getId()%>" <%
                                            if (bean.getId().equals(todoItemList.getUser())) {
                                        %>selected<%
                                                }
                                        %>><%= bean.getName()%></option><%
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
                                calendar.setTime(todoItemList.getExpire_date());
                            %>
                            <input type="text" name="year" value="<%= calendar.get(Calendar.YEAR)%>" size="8">/<input type="text" name="month" value="<%= calendar.get(Calendar.MONTH) + 1%>" size="4">/<input type="text" name="day" value="<%= calendar.get(Calendar.DAY_OF_MONTH)%>" size="4">
                        </td>
                    </tr>
                    <tr>
                        <th class="add_field">
                            完了
                        </th>
                        <td class="add_field">
                            <input type="checkbox" name="finished" value="true" size="8"<%
                                if (todoItemList.getFinished_date() != null) {
                                   %>checked<%
                                        }
                                   %>>完了した
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="add_button">
                            <table border="0">
                                <tr>
                                    <td>
                                        <input type="submit" name="submit" value="作業更新">
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
