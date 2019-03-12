package todo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoItemInfo {

	/**
	 * データベースのToDoユーザーの内容を取得します.
	 * @return	beanList（ArrayList<TodoItemBean>）
	 */
	public ArrayList<TodoItemBean> getToDoItemData() throws SQLException {
		ArrayList<TodoItemBean> beanList = new ArrayList<TodoItemBean>();
		TodoItemBean bean = null;
		TodoItemDao dao = null;
		ResultSet rs = null;

		try {

			// DAOクラスをインスタンス化
			dao = new TodoItemDao();
			// 現在のＴＯＤＯリストを検索
			rs  = dao.selectTodoItem();

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				bean = new TodoItemBean();

				// IDを設定
				bean.setId(rs.getInt("id"));
				// 名称を設定
				bean.setName(TodoUtil.toSafeString(rs.getString("name")));
				// 対象者を設定
				bean.setUser(TodoUtil.toSafeString(rs.getString("user")));
				// 対象者名を設定
				bean.setUserName(TodoUtil.toSafeString(rs.getString("userName")));
				// 実行期限を設定
				bean.setExpire_date(rs.getDate("expire_date"));
				// 完了日を設定
				bean.setFinished_date(rs.getDate("finished_date"));

				// Beanクラスをリストに追加
				beanList.add(bean);
			}

		} finally {

			// 処理終了時に各接続を解除
			dao.close();
		}

		return beanList;
	}

	/**
	 * データベースのToDoユーザーの内容を取得します.
	 * @param	id		TODOリストid
	 * @return	bean	id指定TODOリスト情報
	 */
	public TodoItemBean getToDoItemData(Long id) throws SQLException {
		TodoItemBean bean = null;
		TodoItemDao dao = null;
		ResultSet rs = null;

		try {

			// DAOクラスをインスタンス化
			dao = new TodoItemDao();
			// 現在のＴＯＤＯリストを検索
			rs  = dao.selectTodoItem(id);

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				bean = new TodoItemBean();

				// IDを設定
				bean.setId(rs.getInt("id"));
				// 名称を設定
				bean.setName(TodoUtil.toSafeString(rs.getString("name")));
				// 対象者を設定
				bean.setUser(TodoUtil.toSafeString(rs.getString("user")));
				// 対象者名を設定
				bean.setUserName(TodoUtil.toSafeString(rs.getString("userName")));
				// 実行期限を設定
				bean.setExpire_date(rs.getDate("expire_date"));
				// 完了日を設定
				bean.setFinished_date(rs.getDate("finished_date"));

			}

		} finally {

			// 処理終了時に各接続を解除
			dao.close();
		}

		return bean;
	}

	/**
	 * データベースのToDoユーザーの内容を取得します.
	 * @param	keyword	検索用キーワード
	 * @return	beanList	id指定TODOリスト情報
	 */
	public ArrayList<TodoItemBean> getToDoItemData(String keyword) throws SQLException {
		ArrayList<TodoItemBean> beanList = new ArrayList<TodoItemBean>();
		TodoItemBean bean = null;
		TodoItemDao dao = null;
		ResultSet rs = null;

		try {

			// DAOクラスをインスタンス化
			dao = new TodoItemDao();
			// 現在のＴＯＤＯリストを検索
			rs  = dao.selectTodoItem(keyword);

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				bean = new TodoItemBean();

				// IDを設定
				bean.setId(rs.getInt("id"));
				// 名称を設定
				bean.setName(TodoUtil.toSafeString(rs.getString("name")));
				// 対象者を設定
				bean.setUser(TodoUtil.toSafeString(rs.getString("user")));
				// 対象者名を設定
				bean.setUserName(TodoUtil.toSafeString(rs.getString("userName")));
				// 実行期限を設定
				bean.setExpire_date(rs.getDate("expire_date"));
				// 完了日を設定
				bean.setFinished_date(rs.getDate("finished_date"));

				// Beanクラスをリストに追加
				beanList.add(bean);

			}

		} finally {

			// 処理終了時に各接続を解除
			dao.close();
		}

		return beanList;
	}

	/**
	 * データベースのToDoユーザーの内容を取得します.
	 * @return	userBeanList（ArrayList<LoginUserBean>）
	 */
	public ArrayList<LoginUserBean> getToDoUserData() throws SQLException {
		ArrayList<LoginUserBean> userBeanList = new ArrayList<LoginUserBean>();
		LoginUserBean bean = null;
		TodoItemDao dao = null;
		ResultSet rs = null;

		try {

			// DAOクラスをインスタンス化
			dao = new TodoItemDao();
			// 現在のＴＯＤＯリストを検索
			rs  = dao.selectTodoUser();

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				bean = new LoginUserBean();

				// IDを設定
				bean.setId(TodoUtil.toSafeString(rs.getString("id")));
				// 名称を設定
				bean.setName(TodoUtil.toSafeString(rs.getString("name")));
				// 対象者を設定
				bean.setPassword(TodoUtil.toSafeString(rs.getString("password")));

				// Beanクラスをリストに追加
				userBeanList.add(bean);
			}

		} finally {

			// 処理終了時に各接続を解除
			dao.close();
		}

		return userBeanList;
	}
}
