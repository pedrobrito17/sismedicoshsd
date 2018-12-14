package com.sismed.sismedhsd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="restricao")
public class Restricao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_restricao;
	
	@ManyToMany(mappedBy="restricoes")
	private List<Medico> medico;
	
	public String dia_semana;
	
	private String turno;
	
	public int getId_restricao() {
		return id_restricao;
	}
	public void setId_restricao(int id_restricao) {
		this.id_restricao = id_restricao;
	}
	public List<Medico> getMedico() {
		return medico;
	}
	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}
	public String getDiaSemana() {
		return dia_semana;
	}
	public void setDiaSemana(String diaSemana) {
		this.dia_semana = diaSemana;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}	
	
}
