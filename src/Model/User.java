package Model;

import Helper.DBConection;

public class User {
	private int id, ücret;
	String tcno, name, password, type, telno, email, brans, veltelno, wdate;
	DBConection conn = new DBConection();

	public User(int id, String tcno, String name, String password, String type, String brans, String telno,
			String email, String veltelno, int ücret, String wdate) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.name = name;
		this.password = password;
		this.type = type;
		this.brans = brans;
		this.telno = telno;
		this.email = email;
		this.veltelno = veltelno;
		this.ücret = ücret;
		this.wdate = wdate;
	}

	public int getÜcret() {
		return ücret;
	}

	public void setÜcret(int ücret) {
		this.ücret = ücret;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getVeltelno() {
		return veltelno;
	}

	public void setVeltelno(String veltelno) {
		this.veltelno = veltelno;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcno() {
		return tcno;
	}

	public String getBrans() {
		return brans;
	}

	public void setBrans(String brans) {
		this.brans = brans;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
