package com.academia.modelo;
public class Alumno {
 private final int id,edad,asignaturaId; private final String nombre,apellidos,email;
 public Alumno(int id,String n,String a,String e,int edad,int asignaturaId){this.id=id;nombre=n;apellidos=a;email=e;this.edad=edad;this.asignaturaId=asignaturaId;}
 public int getId(){return id;} public String getNombre(){return nombre;} public String getApellidos(){return apellidos;} public String getEmail(){return email;} public int getEdad(){return edad;} public int getAsignaturaId(){return asignaturaId;}
 @Override public String toString(){return id+" | "+nombre+" "+apellidos+" | "+email+" | edad "+edad+" | asignatura "+asignaturaId;}
}
