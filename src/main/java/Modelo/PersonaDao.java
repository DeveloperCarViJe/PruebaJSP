package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {

	PreparedStatement ps;
	ResultSet rs;
	Conexion c=new Conexion();
	Connection con;
	
	public void insertarDatos(Persona persona) {
	    String sql = "INSERT INTO persona (id, nombres, correo, telefono) VALUES (?, ?, ?, ?)";
	    try {
	        con = c.conectar();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, persona.getId());
	        ps.setString(2, persona.getNom());
	        ps.setString(3, persona.getCorreo());
	        ps.setString(4, persona.getTel());
	        ps.executeUpdate();
	        System.out.println("Data inserted successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void ActualiarDatos(Persona persona) {
		String sql = "UPDATE persona SET nombres = ?, correo = ?, telefono = ? WHERE id = ?";
	    try {
	        con = c.conectar();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, persona.getNom());
	        ps.setString(2, persona.getCorreo());
	        ps.setString(3, persona.getTel());
	        ps.setString(4, persona.getId());
	        ps.executeUpdate();
	        System.out.println("Data actualizado successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	 public void eliminar(String id) {
	        String sql = "DELETE FROM persona WHERE id = ?";
	        try (Connection con = c.conectar();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, id);
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Registro con ID " + id + " eliminado exitosamente.");
	            } else {
	                System.out.println("No se encontró el registro con ID " + id + ".");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
		        try {
		            if (ps != null) ps.close();
		            if (con != null) con.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
	    }
	
	public List<Persona> listar() {
	    List<Persona> lista = new ArrayList<>();
	    String sql = "SELECT * FROM persona";
	    try {
	        con = c.conectar();
	        if (con != null) {
	            System.out.println("Connection established successfully.");
	        } else {
	            System.out.println("Failed to establish connection.");
	        }

	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        if (rs.isBeforeFirst()) {  // Verificar si hay resultados antes de recorrerlos
	            while (rs.next()) {
	                Persona p = new Persona();
	                p.setId(rs.getString("ID"));  // Usa los nombres exactos de las columnas
	                p.setNom(rs.getString("NOMBRES"));
	                p.setCorreo(rs.getString("CORREO"));
	                p.setTel(rs.getString("TELEFONO"));
	                lista.add(p);
	                
	                // Mensaje de depuración
	                System.out.println("ID: " + p.getId());
	                System.out.println("Nombres: " + p.getNom());
	                System.out.println("Correo: " + p.getCorreo());
	                System.out.println("Telefono: " + p.getTel());
	            }
	            System.out.println("Number of records fetched: " + lista.size());
	        } else {
	            System.out.println("No records found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    return lista;
	}


}
