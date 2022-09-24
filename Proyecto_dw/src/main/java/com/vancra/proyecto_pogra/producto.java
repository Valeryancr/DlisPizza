package com.vancra.proyecto_pogra;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class producto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idproducto;
	@Column
	private double precio;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private String imagen;
	
	@OneToMany(mappedBy = "productoid", cascade = CascadeType.ALL)
	private Set<productospedidos> productospedidos;
	
	public int getIdproductos() {
		return idproducto;
	}
	public void setIdproductos(int idproducto) {
		this.idproducto = idproducto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
