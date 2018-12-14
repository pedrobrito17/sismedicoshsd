package com.sismed.sismedhsd.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ferias_medico")
public class FeriasMedico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Medico medico;
	
	@Column
	@NotNull(message="{data.vazia}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dt_inicio;
	
	@Column
	@NotNull(message="{data.vazia}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dt_fim;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDt_inicio() {
		return dt_inicio;
	}

	public void setDt_inicio(Date dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public Date getDt_fim() {
		return dt_fim;
	}

	public void setDt_fim(Date dt_fim) {
		this.dt_fim = dt_fim;
	}
	
	
	
	
}
