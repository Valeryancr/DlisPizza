package com.vancra.proyecto_pogra;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "iddireccion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iddireccion;
	@Column
	private String direccion;
	//* @ManyToOne
	//*@JoinColumn(name = "codigovendedora",nullable=false)
	//*private vendedoras codigovendedora;

	private int userid;
	
	//*public telefonos(vendedoras codigovendedora) {
	//*	this.codigovendedora=codigovendedora;
	//*}
	
	public int getIdDireccion() {
		return iddireccion;
	}
	public void setIdDireccion(int iddireccion) {
		this.iddireccion = iddireccion;
	}
	public int geDUserId() {
		return userid;
	}
	public void setUserId(int userid) {
		this.userid = userid;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	//*@JsonProperty("codigovendedora")
	//*private void unpackNested(Integer codigo) {
	//*    this.codigovendedora = new vendedoras();
	//*    codigovendedora.setCodigo(codigo);
	//*}
}
