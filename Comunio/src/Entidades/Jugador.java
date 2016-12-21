package Entidades;

public class Jugador {
	private String nombre,precio,puntuacioTotal,posicion,equipo,due�o;

	public Jugador(String nombre, String precio, String puntuacioTotal, String posicion, String equipo, String due�o) {
		this.nombre = nombre;
		this.precio = precio;
		this.puntuacioTotal = puntuacioTotal;
		this.posicion = posicion;
		this.equipo = equipo;
		this.due�o=due�o;
	}
	public String getDue�o() {
		return due�o;
	}

	public void setDue�o(String due�o) {
		this.due�o = due�o;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getPuntuacioTotal() {
		return puntuacioTotal;
	}

	public void setPuntuacioTotal(String puntuacioTotal) {
		this.puntuacioTotal = puntuacioTotal;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	
	
}
