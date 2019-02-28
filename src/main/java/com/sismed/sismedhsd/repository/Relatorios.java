package com.sismed.sismedhsd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sismed.sismedhsd.model.Escala;

@Repository
public interface Relatorios extends JpaRepository<Escala, Integer> {
	
	@Query(value = "SELECT count(a.medicom5_crm), b.nome, b.crm FROM escala a "
			+ "INNER JOIN medico b ON a.medicom5_crm = b.crm WHERE month(a.data) = :mes AND "
			+ "year(a.data) = :ano AND b.categoria = :categoria group by b.crm", nativeQuery=true)
	public List<Object> getContagemM5(@Param("mes") int mes, @Param("ano") int ano, 
			@Param("categoria") String categoria);
	
	@Query(value = "SELECT count(a.medico_hro_crm), b.nome, b.crm FROM escala a "
			+ "INNER JOIN medico b ON a.medico_hro_crm = b.crm WHERE month(a.data) = :mes AND "
			+ "year(a.data) = :ano AND b.categoria = :categoria group by b.crm", nativeQuery=true)
	public List<Object> getContagemHro(@Param("mes") int mes, @Param("ano") int ano, 
			@Param("categoria") String categoria);
	
	/*Exceto para sábado e domingo*/
	@Query(value = "SELECT count(turno) contTurno, b.nome, b.crm FROM escala a INNER JOIN medico b " 
			+"INNER JOIN ordem c ON c.medico_crm = b.crm AND a.id = c.escala_id " 
			+"WHERE month(a.data) = :mes AND year(a.data) = :ano AND weekday(a.data)!= 5 AND "
			+ "weekday(a.data)!= 6 AND a.turno = 'manha' AND b.categoria = :categoria group by b.crm", nativeQuery = true)
	public List<Object> getMedicosManha(@Param("mes") int mes, @Param("ano") int ano, 
			@Param("categoria") String categoria);
	
	/* (com execeção de sabado e domingo) */
	@Query(value = "SELECT (:qtdTotal - count(turno)) qtd, a.nome, a.crm from medico a INNER JOIN ordem b "
					+"INNER JOIN escala c ON a.crm = b.medico_crm AND b.escala_id = c.id "
					+ "WHERE month(c.data) = :mes AND year(c.data) = :ano "
					+ "AND weekday(c.data)!= 5 AND weekday(c.data)!= 6 AND c.turno!='noite' "
					+ "group by a.crm", nativeQuery=true)
	public List<Object> getQtdTurnosDeFolga(@Param("qtdTotal") int qtdTotal, @Param("mes") int mes, @Param("ano") int ano);
	
	/*Posição no turno da tarde com exceção de sábado e domingo*/
	@Query(value = "SELECT count(a.ordem) cont, b.nome nome, b.crm FROM ordem a INNER JOIN medico b "
			+ "INNER JOIN escala c ON b.crm = a.medico_crm AND a.escala_id = c.id WHERE c.turno = 'tarde' AND "
			+ "month(c.data) = :mes AND year(c.data) = :ano AND a.ordem = :posicao "
			+ "AND weekday(c.data)!= 5 AND weekday(c.data)!= 6 "
			+ "group by b.crm", nativeQuery=true)
	public List<Object> getQtdOrdemTarde(@Param("mes") int mes, 
			@Param("ano") int ano, @Param("posicao") int posicao);

	/*Posicao no turno da noite com exceção de sábado e domingo*/
	@Query(value = "SELECT count(a.ordem) cont, b.nome nome, b.crm FROM ordem a INNER JOIN medico b "
			+ "INNER JOIN escala c ON b.crm = a.medico_crm AND a.escala_id = c.id WHERE  c.turno = 'noite' AND "
			+ "month(c.data) = :mes AND year(c.data) = :ano AND a.ordem = :posicao "
			+ "AND weekday(c.data)!= 5 AND weekday(c.data)!= 6 "
			+ "group by b.crm", nativeQuery=true)
	public List<Object> getQtdOrdemNoite(@Param("mes") int mes, 
			@Param("ano") int ano, @Param("posicao") int posicao);

	@Query(value = "SELECT count(turno), b.nome, b.crm FROM ordem a INNER JOIN medico b "
					+"INNER JOIN escala c ON b.crm = a.medico_crm AND "
					+"a.escala_id = c.id WHERE month(c.data) = :mes AND year(c.data) = :ano "
					+"AND weekday(c.data) = 5 OR weekday(c.data) = 6 GROUP BY b.crm", nativeQuery = true)
	public List<Object> getCountFds(@Param("mes") int mes, @Param("ano") int ano);

	@Query(value = "SELECT count(turno), b.nome, b.crm FROM ordem a INNER JOIN medico b "
					+"INNER JOIN escala c ON b.crm = a.medico_crm AND "
					+"a.escala_id = c.id WHERE month(c.data) = :mes AND year(c.data) = :ano "
					+ "AND weekday(c.data) != 5 AND weekday(c.data) != 6 AND c.turno = 'tarde' "
					+ "AND b.categoria = :categoria GROUP BY b.crm", nativeQuery = true)
	public List<Object> getContagemTardes(@Param("mes") int mes, @Param("ano") int ano, 
			@Param("categoria") String categoria);
	
	@Query(value = "SELECT count(turno), b.nome, b.crm FROM ordem a INNER JOIN medico b "
			+"INNER JOIN escala c ON b.crm = a.medico_crm AND "
			+"a.escala_id = c.id WHERE month(c.data) = :mes AND year(c.data) = :ano "
			+ "AND weekday(c.data) != 5 AND weekday(c.data) != 6 AND c.turno = 'noite' "
			+ "AND b.categoria = :categoria GROUP BY b.crm", nativeQuery = true)
	public List<Object> getContagemNoites(@Param("mes") int mes, @Param("ano") int ano, 
			@Param("categoria") String categoria);
	
	@Query(value="SELECT count(b.data), a.nome, a.crm FROM medico a INNER JOIN escala b "
			+ "INNER JOIN ordem c ON c.escala_id = b.id AND c.medico_crm = a.crm WHERE b.turno!='noite' AND a.categoria ='socio' AND b.data "
			+ "BETWEEN :data_1 AND :data_2 group by a.crm;", nativeQuery = true)
	public List<Object> getContagemServicoSemanal(@Param("data_1") Date data_1, @Param("data_2") Date data_2);

	
	@Query(value="select count(a.nome), a.nome, a.crm from medico a INNER JOIN escala b ON (a.crm = b.medico_hro_crm OR a.crm = b.medicom5_crm "
			+"OR a.crm = b.medico_eda_crm) WHERE "
			+ "b.data BETWEEN :data AND :data2 group by a.crm;", nativeQuery=true)
	public List<Object> getContagemServicoSemanaHRo(@Param("data") Date data, @Param("data2") Date data2);

	

}
