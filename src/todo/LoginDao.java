package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;

	/**
	 * データベースのログイン者の名前を取得します.
	 * @return	ログイン者情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectLoginUser(String id, String pass) throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sqlSratrment = "";

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo",
					"root",
					"");

			sqlSratrment = "Select "
						 +   "name "
						 + "FROM "
						 +   "todo_user "
						 + "WHERE "
						 +   "id = ? "
						 + "AND "
						 +   "password = ?";

System.out.println(sqlSratrment);
			ps = con.prepareStatement(sqlSratrment);
			ps.setString(1, id);
			ps.setString(2, pass);
			System.out.println(ps.toString());
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * コネクションをクローズします.
	 */
	public void close() {

		try {

			// データベースとの接続を解除する
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch (SQLException se) {

			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
}
