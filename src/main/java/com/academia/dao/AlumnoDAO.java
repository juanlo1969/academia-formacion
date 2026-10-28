package com.academia.dao;
import com.academia.conexion.ConexionBD;import com.academia.modelo.Alumno;import java.sql.*;import java.util.*;
public class AlumnoDAO {
 public List<Alumno> listar()throws SQLException{List<Alumno> l=new ArrayList<>();String q="SELECT * FROM alumnos ORDER BY id";try(Connection c=ConexionBD.getInstancia().getConexion();PreparedStatement p=c.prepareStatement(q);ResultSet r=p.executeQuery()){while(r.next())l.add(new Alumno(r.getInt("id"),r.getString("nombre"),r.getString("apellidos"),r.getString("email"),r.getInt("edad"),r.getInt("asignatura_id")));}return l;}
 public void insertar(String n,String a,String e,int edad,int asignatura)throws SQLException{String q="INSERT INTO alumnos(nombre,apellidos,email,edad,asignatura_id) VALUES(?,?,?,?,?)";try(Connection c=ConexionBD.getInstancia().getConexion();PreparedStatement p=c.prepareStatement(q)){p.setString(1,n);p.setString(2,a);p.setString(3,e);p.setInt(4,edad);p.setInt(5,asignatura);p.executeUpdate();}}
 public boolean eliminar(int id)throws SQLException{try(Connection c=ConexionBD.getInstancia().getConexion();PreparedStatement p=c.prepareStatement("DELETE FROM alumnos WHERE id=?")){p.setInt(1,id);return p.executeUpdate()>0;}}
}
