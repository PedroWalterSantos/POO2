package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/ClinicaVeterinaria";
    private static final String USER = "root";
    private static final String PASSWORD = "root_@01";
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";

   /* public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }*/
    
  static{
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro de Driver");
			e.printStackTrace();
		}
    }
  public static Connection conectar() {
	  Connection conn = null;
	 try {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Conexão ok!");
	} catch (SQLException e) {
		System.out.println("Erro ao conectar ao banco de dados");
		e.printStackTrace();
	}
	 return conn;
  }
    
}
