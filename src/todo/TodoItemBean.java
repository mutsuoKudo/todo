package todo;

import java.io.Serializable;
import java.sql.Date;

public class TodoItemBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String user;
	private String userName;
	private Date expire_date;
	private Date finished_date;

	public TodoItemBean() {
		id = 0;
		name = "";
		user = "";
		expire_date = null;
		finished_date = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public Date getFinished_date() {
		return finished_date;
	}

	public void setFinished_date(Date finished_date) {
		this.finished_date = finished_date;
	}

}
