package com.sismed.sismedhsd.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sismed.sismedhsd.model.Escala;

@Repository
public interface Escalas extends JpaRepository<Escala, Integer>{
	/*ORDER BY b.ordem ASC*/
	@Query(value="SELECT a.crm, a.nome, c.turno, c.data FROM medico a INNER JOIN ordem b "
					+"INNER JOIN escala c ON a.crm = b.medico_crm AND b.escala_id = c.id " 
				    +"WHERE month(c.data) = :mes AND year(c.data) = :ano order by b.ordem ASC", nativeQuery=true)
	List<Object> findByDataAndTurno(@Param("mes") int mes, @Param("ano") int ano);
	
	@Query(value=" SELECT a.nome  from medico a INNER JOIN "
			+ "ordem b INNER JOIN escala c ON a.crm = b.medico_crm AND "
			+ "b.escala_id = c.id WHERE day(c.data) = :dia AND month(c.data) = :mes AND "
			+ "year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	List<String> selectMedicosSalvosNaEscala(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);	
	
	@Query(value="SELECT medicom5_crm from escala c WHERE day(c.data) = :dia AND month(c.data) = :mes AND "
			+ "year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	List<Object> buscarM5DaEscala(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);
	
	@Query(value="SELECT medico_hro_crm from escala c WHERE day(c.data) = :dia AND month(c.data) = :mes AND "
			+ "year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	List<Object> buscarHRODaEscala(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);

	@Query(value="SELECT medico_eda_crm from escala c WHERE day(c.data) = :dia AND month(c.data) = :mes AND "
			+ "year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	List<Object> buscarEDADaEscala(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);
	
	@Query(value="SELECT a.crm, a.nome,c.turno,c.data FROM escala c "
			+"INNER JOIN medico a ON a.crm = c.medico_hro_crm "
			+ "WHERE month(c.data) = :mes AND year(c.data) = :ano", nativeQuery=true)
	List<Object> getEscalaByTurnoAndDataAndHro(@Param("mes") int mes, @Param("ano") int ano);

	@Query(value="SELECT a.crm, a.nome,c.turno,c.data FROM escala c "
			+"INNER JOIN medico a ON a.crm = c.medico_eda_crm "
			+ "WHERE month(c.data) = :mes AND year(c.data) = :ano", nativeQuery=true)
	List<Object> getEscalaByTurnoAndDataAndEda(@Param("mes") int mes, @Param("ano") int ano);
	
	@Query(value="SELECT a.crm, a.nome,c.turno,c.data FROM escala c "
			+"INNER JOIN medico a ON a.crm = c.medicom5_crm "
			+ "WHERE month(c.data) = :mes AND year(c.data) = :ano", nativeQuery=true)
	List<Object> getEscalaByTurnoAndDataAndM5(@Param("mes") int mes, @Param("ano") int ano);
	
	@Query(value="SELECT a.crm FROM medico a "
			+"INNER JOIN escala c ON a.crm = c.medicom5_crm "
			+ "WHERE day(c.data) = :dia AND month(c.data) = :mes AND year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	int getMedicoM5(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);
	
	Escala findByTurnoAndData(String turno, Date data);
	
	@Query(value="SELECT a.crm FROM medico a "
			+"INNER JOIN escala c ON a.crm = c.medico_hro_crm "
			+ "WHERE day(c.data) = :dia AND month(c.data) = :mes AND year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	int getMedicoHro(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);

	@Query(value="SELECT a.crm FROM medico a "
			+"INNER JOIN escala c ON a.crm = c.medico_eda_crm "
			+ "WHERE day(c.data) = :dia AND month(c.data) = :mes AND year(c.data) = :ano AND c.turno = :turno", nativeQuery=true)
	int getMedicoEda(@Param("dia") int dia, @Param("mes") int mes, 
			@Param("ano") int ano, @Param("turno") String turno);

	@Query(value = "SELECT a.nome FROM medico a INNER JOIN escala b INNER JOIN ordem c ON "
					+"a.crm = c.medico_crm AND c.escala_id = b.id WHERE b.data = :data AND b.turno = :turno "
					+"AND c.medico_crm = :crm", nativeQuery = true)
	String verificarMedicoNaEscala(@Param("turno") String turno, @Param("data") Date data, @Param("crm") int crm);
	
}
