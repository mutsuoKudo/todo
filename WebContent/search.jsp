<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="todo.TodoItemBean"%>
<%@ page import="todo.LoginUserBean"%>
<%@ page import="java.util.Calendar" %>
<%@ page errorPage="./error.jsp" %>
<%
    LoginUserBean currentUser = (LoginUserBean) request.getSession().getAttribute("user_db");
%>
<%
    ArrayList<TodoItemBean> todoItemList = (ArrayList<TodoItemBean>) request.getSession()
            .getAttribute("todo_db");
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>検索結果</title>
        <link rel="STYLESHEET" href="./css/todo.css" type="text/css">
    </head>
    <body>
        <h1>検索結果</h1>
        <hr>
        <table border="0">
            <tr>
            <form action="./TodoServlet" method="post">
                <td>
                    <input type="submit" name="submit" value="戻る">
                </td>
            </form>
        </tr>
    </table>
    <%
        if (todoItemList.isEmpty()) {
            // アイテムが存在しない場合
    %>
    <div align="center">作業項目はありません。</div>
    <%
    } else {
    %>
    <table border="0" width="90%" class="list">
        <tr>
            <th>項目名</th>
            <th>担当者</th>
            <th>期限</th>
            <th>完了</th>
            <th colspan="3">操作</th>
        </tr>

        <%-- Beanの要素数分（TODOの数分）行を作成 --%>
        <%
            // 現在時刻を取得(期限比較用: 分以降は0にリセット)
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            long currentTime = calendar.getTimeInMillis();

            for (TodoItemBean bean : todoItemList) {
                String styleAttr = " style=\"";
                if (bean.getFinished_date() != null) {
                    // 完了
                    styleAttr += "background-color: #cccccc;";
                } else if (bean.getUser().equals(currentUser.getId())) {
                    // 自分の作業
                    styleAttr += "background-color: #ffbbbb;";
                }
                if (bean.getExpire_date().getTime() < currentTime && bean.getFinished_date() == null) {
                    // 期限切れかつ未完了
                    styleAttr +=  " color: #ff0000;\"";
                } else {
                	styleAttr +=  " \"";
                }
        %>

        <tr>
            <td <%=styleAttr%>><%=bean.getName()%></td>
            <td <%=styleAttr%>><%=bean.getUserName()%></td>
            <td <%=styleAttr%>><%=bean.getExpire_date()%></td>
            <td <%=styleAttr%>>
                <%
                    if (bean.getFinished_date() != null) {
                %><%=bean.getFinished_date()%> <%
                } else {
                %>未<%
                    }
                %>
            </td>
        <form action="./TodoServlet" method="post">
            <input type="hidden" name="item_id" value="<%=bean.getId()%>">
            <td <%=styleAttr%> align="center">
                <%
                    if (bean.getFinished_date() != null) {
                %> <input type="submit" name="submit" value="未完了"> <%
                } else {
                %> <input type="submit" name="submit" value="完了"> <%
                    }
                %>
            </td>
        </form>
        <form action="./TodoServlet" method="post">
            <input type="hidden" name="item_id" value="<%=bean.getId()%>">
            <td <%=styleAttr%> align="center"><input type="submit" name="submit" value="更新"></td>
        </form>
        <form action="./TodoServlet" method="post">
            <input type="hidden" name="item_id" value="<%=bean.getId()%>">
            <input type="hidden" name="item_name" value="<%=bean.getName()%>">
            <td <%=styleAttr%> align="center"><input type="submit" name="submit" value="削除"></td>
        </form>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>

<table border="0">
    <tr>
    <form action="./TodoServlet" method="post">
        <td>
            <input type="submit" name="submit" value="戻る">
        </td>
    </form>
</tr>
</table>
</body>
</html>