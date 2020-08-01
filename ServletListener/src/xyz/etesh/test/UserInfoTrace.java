package xyz.etesh.test;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserInfoTrace implements HttpSessionBindingListener {
	private String user;
	private UserInfoList container = UserInfoList.getInstance();

	public UserInfoTrace() {
		user = "";
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("上线" + this.user);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("下线" + this.user);
		if (user != "") {
			container.removeUserInfo(user);
		}
	}

}
