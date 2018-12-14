package com.sismed.sismedhsd.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sismed.sismedhsd.model.Medico;

@Repository
public interface Medicos extends JpaRepository<Medico, Integer>{
	
	List<Medico> findByCategoriaAndAtivo(String categoria, boolean ativo);
	List<Medico> findByAtivo(boolean ativo);
	Medico findByNome(String nome);
	
	@Query(value = "SELECT * FROM medico a INNER JOIN medico_restricoes b INNER JOIN restricao c "
			+ "ON a.crm = b.crm_medico AND b.id_restricao = c.id_restricao "
			+ "WHERE c.turno = :turno AND c.dia_semana = :diaSemana AND a.ativo = true AND a.categoria = :categoriaMed", nativeQuery=true)
	List<Medico> findMedicosByTurnoAndDiasemana(@Param("turno") String turno, @Param("diaSemana") String diaSemana,
			@Param("categoriaMed") String categoriaMed);
	
	
	@Query(value = "SELECT b.nome FROM escala a INNER JOIN medico b " 
				+"INNER JOIN ordem c ON c.medico_crm = b.crm AND a.id = c.escala_id " 
				+"WHERE month(a.data) = :mes AND year(a.data) = :ano AND "
				+ "b.categoria = :categoria group by b.nome", nativeQuery=true)
	Set<String> getNomeMedicos(@Param("mes") int mes, @Param("ano") int ano, @Param("categoria") String categoria);
	
	
	
	@Query(value = "SELECT b.nome FROM escala a INNER JOIN medico b ON a.medicom5_crm = b.crm "
				+"WHERE month(a.data) = :mes AND year(a.data) = :ano AND b.categoria = :categoria "
				+ "group by b.nome;",nativeQuery = true)
	Set<String> getNomeM5(@Param("mes") int mes, @Param("ano") int ano, @Param("categoria") String categoria);
	
	@Query(value = "SELECT b.nome FROM escala a INNER JOIN medico b ON a.medico_hro_crm = b.crm "
			+"WHERE month(a.data) = :mes AND year(a.data) = :ano AND b.categoria = :categoria "
			+ "group by b.nome;",nativeQuery = true)
	Set<String> getNomeHro(@Param("mes") int mes, @Param("ano") int ano, @Param("categoria") String categoria);
	
}
