package com.sismed.sismedhsd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.Feriado;
import com.sismed.sismedhsd.repository.Feriados;

@Service
public class FeriadoService {
	
	@Autowired
	private Feriados feriados;

	public List<Feriado> getTodosFeriados() {
		return feriados.findAllOrderByDataDesc();
	}

	public void salvarFeriado(Feriado feriado) {
		feriados.save(feriado);
	}

	public void excluirFeriado(Integer id) {
		feriados.delete(id);
	}

	public Object getFeriadosDoMesAno(int mes, int ano) {
		return feriados.getFeriadosDoMesAno(mes, ano);
	}

}
