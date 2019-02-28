package com.sismed.sismedhsd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="escala")
public class Escala implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@Column
	private String turno;
	
	@OneToOne
	private Medico medicoHro;
	
	@OneToOne
	private Medico medicoM5;

	@OneToOne
	private Medico medicoEda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Medico getMedicoHro() {
		return medicoHro;
	}

	public void setMedicoHro(Medico medicoHro) {
		this.medicoHro = medicoHro;
	}

	public Medico getMedicoM5() {
		return medicoM5;
	}

	public void setMedicoM5(Medico medicoM5) {
		this.medicoM5 = medicoM5;
	}

	public Medico getMedicoEda(){
		return medicoEda;
	}

	public void setMedicoEda(Medico medicoEda){
		this.medicoEda = medicoEda;
	}
	
}