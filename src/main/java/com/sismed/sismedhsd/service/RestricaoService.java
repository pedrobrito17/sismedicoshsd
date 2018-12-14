package com.sismed.sismedhsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sismed.sismedhsd.model.Restricao;
import com.sismed.sismedhsd.repository.Restricoes;

@Service
public class RestricaoService {

	@Autowired
	private Restricoes restricoes;
	
	public List<Restricao> todasRestricoes(){
		return restricoes.findAll();
	}
}
