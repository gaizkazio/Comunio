package Entidades;

public class Usuario {
private String nombre,contrase�a;

public Usuario(String nombreU,String contrase�aU){
	nombre=nombreU;
	contrase�a=contrase�aU;
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
