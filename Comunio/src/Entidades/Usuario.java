package Entidades;

public class Usuario {
private String nombre,contraseña;
private int puntos;

public int getPuntos() {
	return puntos;
}

public void setPuntos(int puntos) {
	this.puntos = puntos;
}

public Usuario(String nombreU,String contraseñaU,int puntos){
	nombre=nombreU;
	contraseña=contraseñaU;
	this.puntos=puntos;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getContraseña() {
	return contraseña;
}

public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}
}
