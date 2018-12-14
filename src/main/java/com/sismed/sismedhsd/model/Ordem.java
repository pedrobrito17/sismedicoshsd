package com.sismed.sismedhsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordem")
public class Ordem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrdem;
	
	@Column(name="ordem")
	private int ordem;
	
	@OneToOne
	private Escala escala;
	
	@OneToOne
	private Medico medico;

	public int getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(int idOrdem) {
		this.idOrdem = idOrdem;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Escala getEscala() {
		return escala;
	}

	public void setEscala(Escala escala) {
		this.escala = escala;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	
	
}
