package todo;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDB {
	public LoginUserBean getUserData(String id, String pass) {

		LoginUserBean bean = null;
		LoginDao dao = null;
		ResultSet rs = null;

		try {
			//有効な入力値か判定
			if(isValidUserID(id) == false) {
                return null;
            }
            if(isValidPassword(pass) == false) {
                return null;
            }
			// DAOクラスをインスタンス化
			dao = new LoginDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs  = dao.selectLoginUser(id, pass);

			while(rs.next()) {

				// 検索結果が存在する場合はbeanに値をセット（結果が1件しか返らないことを想定）
				bean = new LoginUserBean();
				// ID（IDは引数のものをセット）
				bean.setId(id);
				// 名前
				bean.setName(TodoUtil.toSafeString(rs.getString("name")));
				System.out.println(rs.getString("name"));
				//	パスワード
				bean.setPassword(pass);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			// 処理終了時に各接続を解除
			dao.close();
		}

		return bean;
	}

    /**
     * 有効なユーザIDかどうかを判定します。
     * @param name
     * @return
     */
    private boolean isValidUserID(String name) {
        return isAlphaOrDigit(name);
    }

    /**
     * 有効なパスワードかどうかを判定します。
     * @param password
     * @return
     */
    private boolean isValidPassword(String password) {
        return isAlphaOrDigit(password);
    }

    /**
     * 文字列が半角英数字から構成されているかどうかを判定します。
     * @param str
     * @return
     */
    private boolean isAlphaOrDigit(String str) {
        for(int i = 0; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if(isAlphaOrDigit(ch) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字が半角英数字かどうかを判定します。
     * @param ch
     * @return
     */
    private boolean isAlphaOrDigit(char ch) {
        if('A' <= ch && ch <= 'Z') {
            return true;
        }
        if('a' <= ch && ch <= 'z') {
            return true;
        }
        if('0' <= ch && ch <= '9') {
            return true;
        }
        return false;
    }
}
