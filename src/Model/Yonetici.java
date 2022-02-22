package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Yonetici extends User {
	Connection con = conn.ConnDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Yonetici(int id, String tcno, String name, String password, String type, String brans, String telno,
			String email, String veltelno, int ücret, String wdate) {
		super(id, tcno, name, password, type, brans, telno, email, veltelno, ücret, wdate);

	}

	public Yonetici() {

	}

	public ArrayList<User> getOgretmenList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = conn.ConnDb();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'ögretmen'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"), rs.getString("brans"), rs.getString("telno"), rs.getString("email"),
						rs.getString("veltelno"), rs.getInt("ücret"), rs.getString("wdate"));

				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return list;

	}

	public boolean addOgretmen(String tcno, String name, String password, String brans, String telno, String email,
			int ücret, String wdate) throws SQLException {

		String query = "INSERT INTO user " + "(tcno , password,name, type, brans, telno, email,ücret,wdate)  VALUES "
				+ "(?,?,?,?,?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "ögretmen");
			preparedStatement.setString(5, brans);
			preparedStatement.setString(6, telno);
			preparedStatement.setString(7, email);
			preparedStatement.setInt(8, ücret);
			preparedStatement.setString(9, wdate);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean delOgretmen(int id) throws SQLException {

		String query = "DELETE FROM user WHERE id = ? ";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean updateOgretmen(int id, String tcno, String name, String brans) throws SQLException {

		String query = "UPDATE user SET name = ? ,tcno = ?, brans = ? WHERE id = ?";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, brans);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public ArrayList<User> getStudentList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = conn.ConnDb();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'ögrenci'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"), rs.getString("brans"), rs.getString("telno"), rs.getString("email"),
						rs.getString("veltelno"), rs.getInt("ücret"), rs.getString("wdate"));

				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return list;

	}

	public boolean addStudent(String tcno, String name, String brans, String telno, String email, String veltelno,
			int ücret, String wdate) throws SQLException {

		String query = "INSERT INTO user " + "(tcno , name, type, brans, telno, email,veltelno,ücret,wdate )  VALUES "
				+ "(?,?,?,?,?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, "ögrenci");
			preparedStatement.setString(4, brans);
			preparedStatement.setString(5, telno);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, veltelno);
			preparedStatement.setInt(8, ücret);
			preparedStatement.setString(9, wdate);
			preparedStatement.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean delStudent(int id) throws SQLException {

		String query = "DELETE FROM user WHERE id = ? ";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean updateStudent(int id, String tcno, String name, String brans ) throws SQLException {

		String query = "UPDATE user SET name = ? ,tcno = ?, brans = ?  WHERE id = ?";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, brans);
			//preparedStatement.setInt(4, ücret);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean upStudent(int id, int ücret) throws SQLException {

		String query = "UPDATE user SET  ücret = ? WHERE id = ?";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, ücret);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		if (key)
			return true;
		else
			return false;
	}
	
	
}
