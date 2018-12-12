package PanificiModel;

import java.sql.*;

public class Prova {

	public static void main(String[] args) {
		Connection conn = null;
		try {
		    // db parameters
		    String url = "jdbc:mysql://localhost:3306/panifici";
		    // create a connection to the database
		    conn = DriverManager.getConnection(url,"root","");
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} finally {
		 try{
		           if(null != conn)
		             conn.close();
		 }catch(SQLException ex){
		           System.out.println(ex.getMessage());
		 }
		}
	}
}
