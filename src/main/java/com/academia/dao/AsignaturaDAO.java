package com.academia.dao;
import com.academia.conexion.ConexionBD;import java.sql.*;
public class AsignaturaDAO {public void listar()throws SQLException{try(Connection c=ConexionBD.getInstancia().getConexion();PreparedStatement p=c.prepareStatement("SELECT * FROM asignaturas ORDER BY id");ResultSet r=p.executeQuery()){while(r.next())System.out.println(r.getInt("id")+" | "+r.getString("nombre")+" | "+r.getInt("horas")+" horas | profesor "+r.getInt("profesor_id"));}}}
