package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConection;

import Helper.helper;

public class grup extends User {
private String name, sube;
private int id;


 	DBConection conn = new DBConection();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
public grup(String name, String sube, int id) {
	super();
	this.name = name;
	this.sube = sube;
	this.id = id;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public grup() {
	
}


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSube() {
	return sube;
}

public void setSube(String sube) {
	this.sube = sube;
}


public ArrayList<grup> getgrupList() throws SQLException {
	ArrayList<grup> list = new ArrayList<>();
	grup obj;
	Connection con = conn.ConnDb();
	try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM sinif ");
		while (rs.next()) {
			obj = new grup();
			obj.setName(rs.getString("name"));
			obj.setSube(rs.getString("sube"));
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
public ArrayList<grup> getsinifList() throws SQLException {
	ArrayList<grup> list = new ArrayList<>();
	grup obj;
	Connection con = conn.ConnDb();
	try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM sinif ");
		while (rs.next()) {
			obj = new grup();
			obj.setId(rs.getInt("id"));
			obj.setName(rs.getString("name"));
			obj.setSube(rs.getString("sube"));
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
public boolean addgrup( String name, String sube)
		throws SQLException {

	String query = "INSERT INTO sinif " + "(name, sube)  VALUES "
			+ "(?,?)";
	boolean key = false;
	Connection con = conn.ConnDb();
	try {
		st = con.createStatement();
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, sube);
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



public boolean addsinif( String name,  String sube)
		throws SQLException {

	int key = 0;
	int count = 0;
	String query = "INSERT INTO sinif" + "( name,sube)  VALUES"
			+ "(?,?)";

	try {
		Connection con = conn.ConnDb();
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM sinif WHERE name = '" + name + "'");
		while (rs.next()) {
			count++;
			helper.showMsg("Bu kiþinin daha önce bir sinifa atamasý oluþturulmuþ !");
			break;
		}
		if (count == 0) {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, sube);
			preparedStatement.executeUpdate();
			key = 1;

		}

	} catch (SQLException e) {

		e.printStackTrace();
	}
	if (key == 1)
		return true;
	else
		return false;
}

public boolean updateSinif(String sube,String name ) throws SQLException {

	String query = "UPDATE sinif SET  sube = ? WHERE name = ?";
	boolean key = false;
	Connection con = conn.ConnDb();
	try {
		st = con.createStatement();
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, sube);
		preparedStatement.setString(2, name);
	
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

	String query = "DELETE FROM sinif WHERE id = ? ";
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

}

