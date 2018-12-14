package com.sismed.sismedhsd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.FeriasMedico;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.repository.FeriasMedicos;

@Service
public class FeriasMedService {

	@Autowired
	private FeriasMedicos feriasMedicos;
	
	@Transactional
	public void salvarFerias(FeriasMedico feriasMedico) {
		feriasMedicos.save(feriasMedico);
	}

	public List<FeriasMedico> getTodasFerias(Medico medico) {
		return feriasMedicos.findByMedicoOrderByIdDesc(medico);
	}

	public void excluirFerias(Integer id) {
		feriasMedicos.delete(id);
	}

}
