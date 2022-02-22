package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConection;

public class Muhasebe extends User {
	private int id, stu_id, ödenen, money;
	private String stu_name, wdate, status;

	DBConection conn = new DBConection();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Muhasebe(int id, int stu_id, String stu_name, String wdate, int ödenen, String status, int money) {
		super();
		this.id = id;
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.wdate = wdate;
		this.ödenen = ödenen;
		this.status = status;
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Muhasebe() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getÖdenen() {
		return ödenen;
	}

	public void setÖdenen(int ödenen) {
		this.ödenen = ödenen;
	}

	public ArrayList<Muhasebe> getPayList() throws SQLException {
		ArrayList<Muhasebe> list = new ArrayList<>();
		Muhasebe obj;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM muhasebe ");
			while (rs.next()) {
				obj = new Muhasebe();
				obj.setId(rs.getInt("id"));
				obj.setStu_id(rs.getInt("stu_id"));
				obj.setStu_name(rs.getString("stu_name"));
				obj.setWdate(rs.getString("wdate"));
				obj.setÖdenen(rs.getInt("ödenen"));
				obj.setStatus(rs.getString("status"));
				obj.setMoney(rs.getInt("money"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return list;

	}

	public boolean addPayStudent(int stu_id, String stu_name, String wdate, int ödenen, String status, int money)
			throws SQLException {

		String query = "INSERT INTO muhasebe " + "(stu_id,stu_name,wdate,ödenen,status,money )  VALUES "
				+ "(?,?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, stu_id);
			preparedStatement.setString(2, stu_name);
			preparedStatement.setString(3, wdate);
			preparedStatement.setInt(4, ödenen);
			preparedStatement.setString(5, status);
			preparedStatement.setInt(6, money);
			preparedStatement.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			
		}
		if (key)
			return true;
		else
			return false;
	}
	public ArrayList<Muhasebe> getKasaList() throws SQLException {
		ArrayList<Muhasebe> list = new ArrayList<>();
		Muhasebe obj;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT SUM'ödenen' FROM muhasebe");
			while (rs.next()) {
				obj = new Muhasebe();
				//obj.setId(rs.getInt("id"));
				//obj.setStu_id(rs.getInt("stu_id"));
				//obj.setStu_name(rs.getString("stu_name"));
				//obj.setWdate(rs.getString("wdate"));
				obj.setÖdenen(rs.getInt("ödenen"));
				obj.setStatus(rs.getString("status"));
				//obj.setMoney(rs.getInt("money"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return list;

	}
	


}
	

