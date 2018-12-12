package PanificiModel;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOPanifici {
	private final String DBUrl = "jdbc:mysql://localhost:3306/panifici";
	private String user;
	private String passw;
	private Connection c=null;
	
	public DAOPanifici(String user, String passw) {
		this.user = user;
		this.passw = passw;
	}
	private void connectDB() {
		try {
			if (c==null) c=DriverManager.getConnection(DBUrl,user,passw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

	public void closeDB() {
		try {
			c.close();
			c=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Passa i dati del database ad un arrayList
	public List<Panificio> getPanifici() {
		List<Panificio> lp=new ArrayList<Panificio>();
		connectDB();
		try {
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT Citta, Provincia, Regione, Panetteria FROM panifici");
			while (rs.next()) {
				Panificio p=new Panificio();
				p.setCitta(rs.getString("Citta"));
				p.setProvincia(rs.getString("Provincia"));
				p.setRegione(rs.getString("Regione"));
				p.setPanificio(rs.getString("Panetteria"));
				lp.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeDB();
		return lp;
	}
	
	//Seleziona tutte le città di una provincia
	public List<String> getCitta(String provincia) {
		List<String> lp=new ArrayList<String>();
		connectDB();
		try {
			PreparedStatement s=c.prepareStatement("SELECT DISTINCT Citta FROM panifici WHERE Provincia=? ORDER BY 1 ASC");
			s.setString(1, provincia);
			ResultSet rs=s.executeQuery();
			while (rs.next()) {
				lp.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeDB();
		return lp;
	}
	
	public List<String> provincePerCmb(){
		List<String> prov=new ArrayList<String>();
		try{
			connectDB();
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT DISTINCT Provincia FROM panifici");
			while (rs.next()) {
				prov.add(rs.getString("provincia"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		closeDB();
		return prov;
	}
	
	public List<String> getPanificio(String provincia, String citta){
		List<String> lpFinale=new ArrayList<String>();
		List<Panificio> lp=getPanifici();
		for (int i = 0; i < lp.size(); i++) {
			if(provincia.equalsIgnoreCase(lp.get(i).getProvincia()) && citta.equalsIgnoreCase(lp.get(i).getCitta())) {
				lpFinale.add(lp.get(i).getPanificio());
			}
		}
		return lpFinale;
	}
}

	
