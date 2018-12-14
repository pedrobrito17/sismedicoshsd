package com.sismed.sismedhsd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sismed.sismedhsd.model.Ordem;

public interface Ordens extends JpaRepository<Ordem, Integer>{

	Ordem findByMedico_crmAndEscala_id(int crmMedico, int idEscala);
	
	@Query(value = "SELECT * FROM ordem WHERE escala_id = :idEscala", nativeQuery=true)
	List<Ordem> getTodosDaEscalaId(@Param("idEscala") int idEscala);
	
	@Modifying
	@Query(value = "UPDATE ordem SET ordem= :novaOrdem WHERE medico_crm= :crm AND escala_id= :idEscala", nativeQuery=true)
	void updateOrdem(@Param("novaOrdem") int novaOrdem, @Param("crm") int crm, @Param("idEscala") int idEscala);
}
