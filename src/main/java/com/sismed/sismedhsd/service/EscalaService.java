package com.sismed.sismedhsd.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.Escala;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.model.Ordem;
import com.sismed.sismedhsd.model.auxiliar.EscalaView;
import com.sismed.sismedhsd.repository.Escalas;
import com.sismed.sismedhsd.repository.Medicos;
import com.sismed.sismedhsd.repository.Ordens;

@Service
public class EscalaService {
	
	@Autowired
	private Escalas escalas;
	
	@Autowired
	private Medicos medicos;
	
	@Autowired
	private Ordens ordens;
	
	public void salvarMedicoEscala(Escala escala){				
		escalas.save(escala);
	}
	
	public List<EscalaView> buscarMedicosEscaladosNoMes(int mes, int ano) throws ParseException{
		
		return new EscalaView().getLista(escalas.findByDataAndTurno(mes, ano));
	}
	
	public Escala buscarEscalaPorTurnoSalvo(String turno, Date data){				
		Escala escalaSalva = escalas.findByTurnoAndData(turno, data);
		return escalaSalva;
	}
	
	public boolean getM5ParaDataETurno(String turno, Date data){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		int dia = gc.get(Calendar.DATE);
		int mes = gc.get(Calendar.MONTH)+1;
		int ano = gc.get(Calendar.YEAR);
				
		List<Object> m5 = escalas.buscarM5DaEscala(dia, mes, ano, turno);
		
		Iterator<Object> it = m5.iterator();
		while(it.hasNext()){
			if(it.next()!=null){
				return true;
			}			
		}
		return false;
	}

	public boolean getHroParaDataETurno(String turno, Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		int dia = gc.get(Calendar.DATE);
		int mes = gc.get(Calendar.MONTH)+1;
		int ano = gc.get(Calendar.YEAR);
				
		List<Object> hro = escalas.buscarHRODaEscala(dia, mes, ano, turno);
		
		Iterator<Object> it = hro.iterator();
		while(it.hasNext()){
			if(it.next()!=null){
				return true;
			}			
		}
		return false;
	}

	public boolean getEdaParaDataETurno(String turno, Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		int dia = gc.get(Calendar.DATE);
		int mes = gc.get(Calendar.MONTH)+1;
		int ano = gc.get(Calendar.YEAR);
				
		List<Object> hro = escalas.buscarEDADaEscala(dia, mes, ano, turno);
		
		Iterator<Object> it = hro.iterator();
		while(it.hasNext()){
			if(it.next()!=null){
				return true;
			}			
		}
		return false;
	}

	public Escala buscarEscalaPorId(Integer id) {
		return escalas.findOne(id);
	}

	public List<EscalaView> getEscalasComM5Salvos(int mes, int ano) throws ParseException {
		
		return new EscalaView().getLista(escalas.getEscalaByTurnoAndDataAndM5(mes, ano));
	}
	
	public List<EscalaView> getEscalasComHroSalvos(int mes, int ano) throws ParseException {

		return new EscalaView().getLista(escalas.getEscalaByTurnoAndDataAndHro(mes, ano));
		
	}

	public List<EscalaView> getEscalasComEdaSalvos(int mes, int ano) throws ParseException {

		return new EscalaView().getLista(escalas.getEscalaByTurnoAndDataAndEda(mes, ano));
		
	}



	@Transactional
	public void removerMedicoDaEscala(Integer crmMedico, int idEscala) {
		Medico medico = medicos.findOne(crmMedico);
		Escala escala = escalas.findOne(idEscala);
		
		if(medico == escala.getMedicoHro()){
			escala.setMedicoHro(null);
			escalas.save(escala);
		}
		else if(medico == escala.getMedicoM5()){
			escala.setMedicoM5(null);
			escalas.save(escala);
		}else if(medico == escala.getMedicoEda()){
			escala.setMedicoEda(null);
			escalas.save(escala);
		}else{
			
			Ordem ordem = ordens.findByMedico_crmAndEscala_id(crmMedico, idEscala);
			if(ordem == null){
				return;
			}			
			int posicao = ordem.getOrdem();
			ordens.delete(ordem);					
			
			List<Ordem> lista = ordens.getTodosDaEscalaId(idEscala);
			for(Ordem order : lista){
				if(order.getOrdem() > posicao){
					int novaPosicao = order.getOrdem() - 1;
					order.setOrdem(novaPosicao);
					ordens.save(order);
				}
			}
		}
	}
	
	

}
