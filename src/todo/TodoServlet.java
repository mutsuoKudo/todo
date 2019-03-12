package todo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TpdoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 今回はdoPostで処理
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ① クリックされたボタンの判定
		// ①-1 ボタン名の文字化けを防ぐために文字コードを設定してから取得
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("submit");

		// ② 画面移動の準備
		HttpSession session = request.getSession(); // セッション
		RequestDispatcher rd = null; // 画面の情報

		if ("ログイン".equals(btn)) {

			//ToDoリストを表示するメソッドを実行
			try {
				displayTodoList(request, response, rd, session);
			} catch (ServletException e) {
				doError(request, response, rd, e.toString());
			} catch (IOException e) {
				doError(request, response, rd, e.toString());
			}
		} else if ("戻る".equals(btn))

		{

			//ToDoリストを表示するメソッドを実行
			try {
				displayTodoList(request, response, rd, session);
			} catch (ServletException e) {
				doError(request, response, rd, e.toString());
			} catch (IOException e) {
				doError(request, response, rd, e.toString());
			}

		} else if ("作業登録".equals(btn)) {
			try {

				TodoItemInfo todoiteminfo = new TodoItemInfo();

				ArrayList<LoginUserBean> userBeanList = todoiteminfo.getToDoUserData();

				session.setAttribute("todoUser_db", userBeanList);

				rd = request.getRequestDispatcher("./add.jsp");

				rd.forward(request, response);

			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			}

		} else if ("登録".equals(btn)) {

			String todoName = request.getParameter("name");
			String todoUser = request.getParameter("user_id");
			String todoYear = request.getParameter("year");
			String todoMonth = request.getParameter("month");
			String todoDay = request.getParameter("day");
			Date expireDate = getDate(todoYear, todoMonth, todoDay);

			if (todoName.isEmpty() || todoUser.isEmpty() || todoYear.isEmpty()
					|| todoMonth.isEmpty() || todoDay.isEmpty()) {
				doError(request, response, rd, "不正な入力値です。");
			}

			TodoItemDao dao = new TodoItemDao();

			try {

				// ＴＯＤＯリストを登録
				dao.insertTodoList(todoName, todoUser, expireDate);

			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			} finally {

				try {

					dao.close();

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					doError(request, response, rd, e.toString());
				}
			}

			//ToDoリストを表示するメソッドを実行
			try {
				displayTodoList(request, response, rd, session);
			} catch (ServletException e) {
				doError(request, response, rd, e.toString());
			} catch (IOException e) {
				doError(request, response, rd, e.toString());
			}

		} else if ("検索".equals(btn)) {
			try {

				String keyword = request.getParameter("keyword");

				//キーワードをセッションに設定
				session.setAttribute("keyword", keyword);

				TodoItemInfo todoiteminfo = new TodoItemInfo();

				ArrayList<TodoItemBean> beanList = todoiteminfo.getToDoItemData(keyword);

				session.setAttribute("todo_db", beanList);

				// 限定検索モードに設定
				session.setAttribute("list_type", "limited");

				rd = request.getRequestDispatcher("./search.jsp");

				rd.forward(request, response);

			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			}

		} else if ("未完了".equals(btn) || "完了".equals(btn)) {

			long id = Long.parseLong(request.getParameter("item_id"));
			boolean todoFinished = false;

			if ("未完了".equals(btn)) {
				todoFinished = true;
			} else {
				todoFinished = false;
			}

			TodoItemDao dao = new TodoItemDao();

			try {

				// ＴＯＤＯリストの完了を更新
				dao.updateFinishStatus(id, todoFinished);

				//遷移元によって、Todoリストまたは検索リストに遷移

				displaySearchOrTodo(request, response, rd, session);
			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			} finally {

				try {

					dao.close();

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					doError(request, response, rd, e.toString());
				}
			}

		} else if ("更新".equals(btn)) {
			try {

				String id = request.getParameter("item_id");

				TodoItemInfo todoiteminfo = new TodoItemInfo();

				ArrayList<LoginUserBean> userBeanList = todoiteminfo.getToDoUserData();

				TodoItemBean todoBean = todoiteminfo.getToDoItemData(Long.parseLong(id));

				session.setAttribute("todoUser_db", userBeanList);

				session.setAttribute("todo_db", todoBean);

				rd = request.getRequestDispatcher("./edit.jsp");

				rd.forward(request, response);

			} catch (SQLException e) {

				doError(request, response, rd, e.toString());
			}

		} else if ("作業更新".equals(btn)) {

			long id = Long.parseLong(request.getParameter("item_id"));
			String todoName = request.getParameter("name");
			String todoUser = request.getParameter("user_id");
			String todoYear = request.getParameter("year");
			String todoMonth = request.getParameter("month");
			String todoDay = request.getParameter("day");
			Date expireDate = getDate(todoYear, todoMonth, todoDay);
			String todoFinished = request.getParameter("finished");

			TodoItemBean todoBean = (TodoItemBean) session.getAttribute("todo_db");
			Date finishedDate = todoBean.getFinished_date();

			TodoItemDao dao = new TodoItemDao();

			try {

				// ＴＯＤＯリストを更新
				dao.updateTodoList(id, todoName, todoUser, expireDate, todoFinished, finishedDate);

				//遷移元によって、Todoリストまたは検索リストに遷移

				displaySearchOrTodo(request, response, rd, session);
			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			} finally {

				try {

					dao.close();

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					doError(request, response, rd, e.toString());
				}
			}

		} else if ("削除".equals(btn)) {
			String id = request.getParameter("item_id");
			String name = request.getParameter("item_name");

			session.setAttribute("todo_id", id);
			session.setAttribute("todo_name", name);

			rd = request.getRequestDispatcher("./delete.jsp");

			rd.forward(request, response);
		} else if ("作業削除".equals(btn)) {
			long id = Long.parseLong(request.getParameter("item_id"));

			TodoItemDao dao = new TodoItemDao();

			try {

				// ＴＯＤＯリストを削除
				dao.deleteTodoList(id);

				//遷移元によって、Todoリストまたは検索リストに遷移

				displaySearchOrTodo(request, response, rd, session);

			} catch (SQLException e) {

				doError(request, response, rd, e.toString());

			} finally {

				try {

					dao.close();

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					doError(request, response, rd, e.toString());
				}
			}
		}

	}

	/**
	 * 日付オブジェクトを取得します。
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws ServletException
	 */
	private Date getDate(String year, String month, String day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(day));

			return new Date(calendar.getTimeInMillis());
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * エラーを表示します。
	 *
	 * @param req
	 * @param resp
	 * @param message
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doError(HttpServletRequest request, HttpServletResponse response, RequestDispatcher rd,
			String message) throws ServletException, IOException {
		request.setAttribute("message", message);

		// エラーを表示する
		rd = request.getRequestDispatcher("./error.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @param rd
	 * @param session
	 * @throws IOException
	 * @throws ServletException
	 */
	private void displayTodoList(HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher rd, HttpSession session) throws ServletException, IOException {
		try {
			TodoItemInfo todoiteminfo = new TodoItemInfo();

			// ③-1-4 IＴＯＤＯリスト情報をモデルに格納
			ArrayList<TodoItemBean> beanList = todoiteminfo.getToDoItemData();

			// setAttributeメソッドを使ってセッションに情報をセット
			// モデル（ＴＯＤＯ情報）
			session.setAttribute("todo_db", beanList);
			// 全件検索モードに設定
			session.setAttribute("list_type", "all");
			// リスト一覧画面に移動
			rd = request.getRequestDispatcher("./list.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {

			doError(request, response, rd, e.toString());

		}
	}

	/**
	 * @param request
	 * @param response
	 * @param rd
	 * @param session
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	private void displaySearchOrTodo(HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher rd, HttpSession session) throws ServletException, IOException, SQLException {
		TodoItemInfo todoiteminfo = new TodoItemInfo();

		//  つぎに表示させる画面（ビュー）を指定
		String listType = (String) session.getAttribute("list_type");

		if (listType.contentEquals("all")) {

			// IＴＯＤＯリスト情報をモデルに格納
			ArrayList<TodoItemBean> beanList = todoiteminfo.getToDoItemData();
			// setAttributeメソッドを使ってセッションに情報をセット
			// モデル（ＴＯＤＯ情報）
			session.setAttribute("todo_db", beanList);
			// 全件検索モードに設定
			session.setAttribute("list_type", "all");
			rd = request.getRequestDispatcher("./list.jsp");

		} else {

			String keyword = (String) session.getAttribute("keyword");
			ArrayList<TodoItemBean> beanList = todoiteminfo.getToDoItemData(keyword);

			session.setAttribute("todo_db", beanList);
			// 限定検索モードに設定
			session.setAttribute("list_type", "limited");
			rd = request.getRequestDispatcher("./search.jsp");
		}
		rd.forward(request, response);
	}
}
