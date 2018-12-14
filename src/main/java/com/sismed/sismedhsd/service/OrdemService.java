package com.sismed.sismedhsd.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sismed.sismedhsd.model.Ordem;
import com.sismed.sismedhsd.repository.Ordens;

@Service
public class OrdemService {

	@Autowired
	private Ordens ordens;
	
	public void salvarOrdemEscala(Ordem ordem) {
		ordens.save(ordem);
	}
	
	@Transactional
	public void reordenar(ArrayList<Integer> crmsMed, int idEscala){
		for(int i = 0 ; i < crmsMed.size() ; i++){
			ordens.updateOrdem((i+1), crmsMed.get(i), idEscala);
		}
	}

}
