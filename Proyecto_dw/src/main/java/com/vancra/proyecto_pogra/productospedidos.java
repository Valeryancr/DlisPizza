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
public class productospedidos implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int iddetalleproducto;
	@Column
	private int cantidad;
	@Column
	private String pnombre;
	@Column
	private double pcosto;
	@Column
	private double subtotal;
	
	private int productoid;
	private int compraid;
	
	
	public int getCompraid() {
		return compraid;
	}
	public void setCompraid(int compraid) {
		this.compraid = compraid;
	}
	public int getIdDetalleProducto() {
		return iddetalleproducto;
	}
	public void setIdDetalleProducto(int iddetalleproducto) {
		this.iddetalleproducto = iddetalleproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getProductoid() {
		return productoid;
	}
	public void setProductoid(int productoid) {
		this.productoid = productoid;
	}
	public String getPnombre() {
		return pnombre;
	}
	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}
	public double getPcosto() {
		return pcosto;
	}
	public void setPcosto(double pcosto) {
		this.pcosto = pcosto;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}