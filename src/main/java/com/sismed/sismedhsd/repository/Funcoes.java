package com.sismed.sismedhsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sismed.sismedhsd.model.Funcao;

@Repository
public interface Funcoes extends JpaRepository<Funcao, Integer>{

}
