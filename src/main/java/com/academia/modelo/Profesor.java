package com.academia.modelo;
public class Profesor {
 private final int id; private final String nombre,apellidos,especialidad;
 public Profesor(int id,String n,String a,String e){this.id=id;nombre=n;apellidos=a;especialidad=e;}
 @Override public String toString(){return id+" | "+nombre+" "+apellidos+" | "+especialidad;}
}
