package todo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class TodoItemDao {

	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	/**
	 * データベースのToDoリストの内容を取得します.
	 * @return	ToDoリスト情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectTodoItem() throws SQLException {

			String sqlSratrment = "";

			con = getConnection();

			sqlSratrment = "Select "
					+ " ti.id,"
					+ " ti.name,"
					+ " ti.user,"
					+ " tu.name AS userName ,"
					+ " ti.expire_date,"
					+ " ti.finished_date "
					+ "FROM "
					+ " todo_item ti "
					+ "LEFT JOIN "
					+ " todo_user tu "
					+ "ON "
					+ " ti.user = tu.id ";

			ps = con.prepareStatement(sqlSratrment);
			System.out.println(ps.toString());
			rs = ps.executeQuery();

		return rs;
	}

	/**
	 * データベースのToDoリストの内容を取得します.
	 * @param  keyword 検索文字列
	 * @return	ToDoリスト情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectTodoItem(String keyword) throws SQLException {

			String sqlSratrment = "";

			con = getConnection();

			sqlSratrment = "Select "
					+ " ti.id,"
					+ " ti.name,"
					+ " ti.user,"
					+ " tu.name AS userName ,"
					+ " ti.expire_date,"
					+ " ti.finished_date "
					+ "FROM "
					+ " todo_item ti "
					+ "LEFT JOIN "
					+ " todo_user tu "
					+ "ON "
					+ " ti.user = tu.id "
					+ "WHERE "
					+ " ti.name LIKE '%"
					+ keyword
					+ "%' "
					+ "OR "
					+ " tu.name LIKE '%"
					+ keyword
					+ "%'";

			System.out.println(sqlSratrment);

			ps = con.prepareStatement(sqlSratrment);

			rs = ps.executeQuery();
			System.out.println(rs.getRow());

		return rs;
	}

	/**
	 * データベースのToDoリストの内容を取得します.
	 * @param	id	ToDoリストID
	 * @return		ID指定リスト情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectTodoItem(Long id) throws SQLException {

			String sqlSratrment = "";

			con = getConnection();

			sqlSratrment = "Select "
					+ " ti.id,"
					+ " ti.name,"
					+ " ti.user,"
					+ " tu.name AS userName ,"
					+ " ti.expire_date,"
					+ " ti.finished_date "
					+ "FROM "
					+ " todo_item ti "
					+ "LEFT JOIN "
					+ " todo_user tu "
					+ "ON "
					+ " ti.user = tu.id "
					+ "WHERE "
					+ "ti.id = ?";

			ps = con.prepareStatement(sqlSratrment);
			ps.setLong(1, id);
			System.out.println(ps.toString());
			rs = ps.executeQuery();

		return rs;
	}

	/**
	 * データベースのToDoユーザーの内容を取得します.
	 * @return	ToDoユーザー情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectTodoUser() throws SQLException {

			String sqlSratrment = "";

			con = getConnection();

			sqlSratrment = "Select "
					+ "id,"
					+ "name,"
					+ "password "
					+ "FROM "
					+ "todo_user";

			ps = con.prepareStatement(sqlSratrment);
			System.out.println(ps.toString());
			rs = ps.executeQuery();

		return rs;
	}

	/**
	 * TODOリストを追加します.
	 * @param name	リスト名
	 * @param user 	担当者
	 * @param expire_date	期限
	 */
	public void insertTodoList(String name, String user, Date expire_date) throws SQLException {

			// データベースと接続（
			con = getConnection();

			// SQL文を生成
			System.out.println(name);
			ps = con.prepareStatement("insert into todo_item (name, user, expire_date) values (?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, user);
			ps.setDate(3, expire_date);
			System.out.println(ps.toString());

			ps.executeUpdate();

	}

	/**
	 * TODOリストを更新します.
	 * @param name	リスト名
	 * @param user 	担当者
	 * @param expire_date	期限
	 * @param finished 完了状態
	 */
	public void updateTodoList(Long id, String name, String user, Date expire_date,String finished,Date finishedDate) throws SQLException {

			// データベースと接続（
			con = getConnection();

			//完了状態を見て完了なら完了日を生成

			Date newFinishedDate = null;

			if ("true".equals(finished)) {
				if (finishedDate == null) {
					Calendar calendar = Calendar.getInstance();
					newFinishedDate = new Date(calendar.getTimeInMillis());
				}
			} else {
				newFinishedDate = null;
			}

			// SQL文を生成
			System.out.println(name);
			ps = con.prepareStatement("update todo_item set name = ?, user = ?, expire_date = ?, finished_date = ? WHERE id = ?");
			ps.setString(1, name);
			ps.setString(2, user);
			ps.setDate(3, expire_date);
			ps.setDate(4, newFinishedDate);
			ps.setLong(5, id);
			System.out.println(ps.toString());

			ps.executeUpdate();

	}

	/**
	 * TODOリストを更新します.
	 * @param name	リスト名
	 * @param user 	担当者
	 * @param expire_date	期限
	 * @param finished 完了状態
	 */
	public void updateFinishStatus(Long id, boolean finished) throws SQLException {

			// データベースと接続（
			con = getConnection();

			//完了状態を見て完了なら完了日を生成

			Date newFinishedDate = null;

			if (finished) {
					newFinishedDate = null;
			} else {
				Calendar calendar = Calendar.getInstance();
				newFinishedDate = new Date(calendar.getTimeInMillis());
			}

			// SQL文を生成
			ps = con.prepareStatement("update todo_item set finished_date = ? WHERE id = ?");
			ps.setDate(1, newFinishedDate);
			ps.setLong(2, id);
			System.out.println(ps.toString());

			ps.executeUpdate();

	}

	/**
	 * TODOリストを更新します.
	 * @param name	リスト名
	 * @param user 	担当者
	 * @param expire_date	期限
	 * @param finished 完了状態
	 */
	public void deleteTodoList(Long id) throws SQLException {

			// データベースと接続（
			con = getConnection();

			// SQL文を生成
			ps = con.prepareStatement("delete FROM todo_item WHERE id = ?");
			ps.setLong(1, id);
			System.out.println(ps.toString());

			ps.executeUpdate();

	}

	/**
	 * コネクションを生成します。
	 *
	 * @return コネクション
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		// Connectionの準備
		if (con != null) {
			return con;
		}
		try {
			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo?useUnicode=true&characterEncoding=utf8",
					"root",
					"");

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
		return con;
	}

	/**
	 * コネクションをクローズします.
	 */
	public void close() throws SQLException {

			// データベースとの接続を解除する
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}

	}
}
