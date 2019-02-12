package model;

public class MemberVo {
	private String name;
	private String userid;
	private String passwd;
	private String repasswd;
	private String email;
	private String tel;
	private String newpasswd;
	private String newrepasswd;
	
	
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	public String getNewrepasswd() {
		return newrepasswd;
	}
	public void setNewrepasswd(String newrepasswd) {
		this.newrepasswd = newrepasswd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRepasswd() {
		return repasswd;
	}
	public void setRepasswd(String repasswd) {
		this.repasswd = repasswd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
