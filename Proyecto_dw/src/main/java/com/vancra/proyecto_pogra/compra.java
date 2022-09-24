package com.vancra.proyecto_pogra;

import java.io.Serializable;
import java.time.LocalDate;
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
public class compra implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idcompra;
	@Column
	private String direccionentrega;
	@Column
	private char domicilio;
	@Column
	private double total;
	@Column
	private int usuariouserid;
	@Column
	private LocalDate fecha;
	@Column
	private boolean completed;

	@OneToMany(mappedBy = "compraid", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<productospedidos> productospedidos;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public int getUsuarioUserId() {
		return usuariouserid;
	}
	public void setUsuarioUserId(int usuariouserid) {
		this.usuariouserid = usuariouserid;
	}
	public int getCompraId() {
		return idcompra;
	}
	public void setCompraId(int idcompra) {
		this.idcompra = idcompra;
	}
	public String getDireccionEntrega() {
		return direccionentrega;
	}
	public void setDireccionEntrega(String direccionentrega) {
		this.direccionentrega = direccionentrega;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean getCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public char getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(char domicilio) {
		this.domicilio = domicilio;
	}
	
	public List<productospedidos> getproductospedidos() {
        return productospedidos;
    }
	public void setProductospedidos(List<productospedidos> productospedidos) {
		this.productospedidos = productospedidos;
	}
}
