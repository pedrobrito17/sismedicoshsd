package com.sismed.sismedhsd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByMedicoOrderByDataDesc(Medico medico);
	
	@Query(value = "SELECT b.nome FROM pedidos a INNER JOIN medico b ON a.medico_crm = b.crm "
				+"WHERE a.tipo_pedido=false AND (a.turno = :turno OR a.turno = 'todos') AND a.data = :data", nativeQuery=true)
	List<String> getPedidosNaoTrabalhar(@Param("data") Date data, @Param("turno") String turno);

}
