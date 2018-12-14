package com.sismed.sismedhsd.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sismed.sismedhsd.model.FeriasMedico;
import com.sismed.sismedhsd.model.Medico;

public interface FeriasMedicos extends JpaRepository<FeriasMedico, Integer>{

	List<FeriasMedico> findByMedicoOrderByIdDesc(Medico medico);
	
	@Query(value="select a.crm FROM medico a INNER JOIN ferias_medico b ON a.crm = b.medico_crm WHERE :data " 
					+"BETWEEN b.dt_inicio AND b.dt_fim", nativeQuery = true)
	int[] getMedicosDeFerias(@Param("data") Date data);

}
