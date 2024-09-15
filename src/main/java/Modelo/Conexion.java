package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String user = "SYS AS SYSDBA";
    String password = "Admin123";
    
	public Connection conectar() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");
		} catch (Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
	