package com.sismed.sismedhsd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@OneToOne
	private Medico medico;
	
	@NotNull(message="{data.vazia}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@NotBlank(message="{turno.vazio}")
	private String turno;
	
	private boolean tipo_pedido;

	public int getIdPedidos() {
		return id;
	}

	public void setIdPedidos(int id) {
		this.id = id;
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public boolean getTipo_pedido() {
		return tipo_pedido;
	}

	public void setTipo_pedido(boolean tipo_pedido) {
		this.tipo_pedido = tipo_pedido;
	}
	
	
}
