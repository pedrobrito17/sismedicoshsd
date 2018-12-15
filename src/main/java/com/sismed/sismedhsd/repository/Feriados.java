package com.sismed.sismedhsd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sismed.sismedhsd.model.Feriado;

@Repository
public interface Feriados extends JpaRepository<Feriado, Integer> {
	
	@Query(value="SELECT * FROM feriado a WHERE month(a.data) = :mes AND year(a.data) = :ano", nativeQuery=true)
	List<Feriado> getFeriadosDoMesAno(@Param("mes") int mes, @Param("ano") int ano);
	
	@Query(value="select * from feriado a order by data desc;", nativeQuery=true)
	List<Feriado> findAllOrderByDataDesc();

}
