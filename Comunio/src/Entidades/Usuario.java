package Entidades;

public class Usuario {
private String nombre,contrase�a;
private int puntos;

public int getPuntos() {
	return puntos;
}

public void setPuntos(int puntos) {
	this.puntos = puntos;
}

public Usuario(String nombreU,String contrase�aU,int puntos){
	nombre=nombreU;
	contrase�a=contrase�aU;
	this.puntos=puntos;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getContrase�a() {
	return contrase�a;
}

public void setContrase�a(String contrase�a) {
	this.contrase�a = contrase�a;
}
}
