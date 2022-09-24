package com.vancra.proyecto_pogra;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userid;
	@Column
	private String username;
	@Column
	private String correo;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private int telefono;
	@Column
	private String password;
	
	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<direccion> direcciones;
	
	public int getUserId() {
		return userid;
	}
	public void setUserId(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<direccion> getDireccion() {
        return direcciones;
    }
    public void setDireccion(List<direccion> direcciones) {
        this.direcciones = direcciones;
    }
    
}
