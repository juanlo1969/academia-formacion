package com.academia.modelo;
public class Asignatura {
 private final int id,horas,profesorId; private final String nombre;
 public Asignatura(int id,String n,int h,int p){this.id=id;nombre=n;horas=h;profesorId=p;}
 @Override public String toString(){return id+" | "+nombre+" | "+horas+" horas | profesor "+profesorId;}
}
