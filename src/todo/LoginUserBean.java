package todo;

import java.io.Serializable;

public class LoginUserBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id; // ID
	private String name; // 名前
	private String password; // パスワード

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します.
	 */
	public LoginUserBean() {
		this.id = "";
		this.name = "";
		this.password = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
