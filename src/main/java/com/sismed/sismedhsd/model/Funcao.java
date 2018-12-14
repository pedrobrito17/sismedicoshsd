package com.sismed.sismedhsd.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="funcao")
public class Funcao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_funcao;
	
	@ManyToMany(mappedBy="funcoes")
	private List<Medico> medico;
	
	@NotNull(message="{funcao.vazia}")
	private String funcao; //m5 ou hro

	public int getId_funcao() {
		return id_funcao;
	}

	public void setId_funcao(int id_funcao) {
		this.id_funcao = id_funcao;
	}

	public List<Medico> getMedico() {
		return medico;
	}

	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
}
