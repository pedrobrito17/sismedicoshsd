package com.sismed.sismedhsd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="medico")
public class Medico implements Serializable{
	private static final long serialVersionUID = -7945478999546152161L;

	@Id
	@NotNull(message="{crm.vazio}")
	@Min(100)
	private int crm;
	
	@NotBlank(message="{nome.vazio}")
	public String nome;
	
	@NotNull(message="{categoria.vazia}")
	private String categoria;
	
	private boolean ativo = true;
	
	@JsonIgnore //Faz o json ignorar a conversao. Não quero estas relacoes.
	@ManyToMany
    @JoinTable(name="medico_funcoes", joinColumns=
    {@JoinColumn(name="crm_medico")}, inverseJoinColumns=
      {@JoinColumn(name="id_funcao")})
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	
	@JsonIgnore
	@ManyToMany
    @JoinTable(name="medico_restricoes", joinColumns=
    {@JoinColumn(name="crm_medico")}, inverseJoinColumns=
      {@JoinColumn(name="id_restricao")})
	private List<Restricao> restricoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	public List<Restricao> getRestricoes() {
		return restricoes;
	}
	public void setRestricoes(List<Restricao> restricoes) {
		this.restricoes = restricoes;
	}
	

}
