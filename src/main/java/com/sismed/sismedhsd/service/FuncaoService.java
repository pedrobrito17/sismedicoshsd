package com.sismed.sismedhsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sismed.sismedhsd.model.Funcao;
import com.sismed.sismedhsd.repository.Funcoes;

@Service
public class FuncaoService {
	
	@Autowired
	private Funcoes funcoes;

	public List<Funcao> todasFuncoes(){
		return funcoes.findAll();
	}

}
