package dao;

import model.Loginmodel;
import util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LoginDAOImpl {
	
      
	public boolean Logar(Loginmodel loginmodel) throws SQLException {
		
	  boolean loginBemSucedido = false;
	  String sql = "SELECT *FROM Login WHERE usuario = ? AND senha = ?";
	  
	  
	  try(Connection conn = ConexaoBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)){
		  pst.setString(1, loginmodel.getUsuario());
		  pst.setString(2, loginmodel.getSenha()); 
	
		  ResultSet rs = pst.executeQuery();
			  if(rs.next()) {
				  loginBemSucedido = true;
				  
			  }
			  
		  
	  }
	  return loginBemSucedido;
		
	}
	
	public void AdicionarLogin(Loginmodel loginmodel) throws SQLException{
		String sql = "INSERT INTO Login (usuario,senha,roleLogin) VALUES(?,?,?)";
		
		try(Connection conn = ConexaoBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, loginmodel.getUsuario());
			pst.setString(2, loginmodel.getSenha());
			pst.setString(3, loginmodel.getRoleLogin());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				
			}
		}
	}
	
	
	

}
