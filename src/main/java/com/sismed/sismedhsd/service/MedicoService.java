package com.sismed.sismedhsd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.repository.Escalas;
import com.sismed.sismedhsd.repository.FeriasMedicos;
import com.sismed.sismedhsd.repository.Medicos;
import com.sismed.sismedhsd.repository.Pedidos;

@Service
public class MedicoService {
	
	@Autowired
	private Medicos medicos;
	
	@Autowired
	private Escalas escalas;
	
	@Autowired
	private FeriasMedicos feriasMedicos;
	
	@Autowired
	private Pedidos pedidos;
	
	public Medico getMedico(int crm){
		return medicos.findOne(crm);
	}
	
	@Transactional
	public List<Medico> getMedicoPorTurnoAndDiasemana(String turno, String diaSemana, Date data, String categoriaMed){
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = calendar.get(GregorianCalendar.MONTH)+1;
		int ano = calendar.get(GregorianCalendar.YEAR);	
		
		//Recupera os medicos salvos na escala
		List<String> medicosSalvosNaEscala = escalas.selectMedicosSalvosNaEscala(dia,mes,ano,turno);

		//Recupera os medicos para a restrição solicitada
		List<Medico> medicosParaEscala = medicos.findMedicosByTurnoAndDiasemana(turno, diaSemana, categoriaMed);
		
		//Recupera os medicos de férias para este dia
		int[] medicosDeFerias = feriasMedicos.getMedicosDeFerias(data);
		
		//Recupera os pedidos para não trabalhar
		List<String> medicoPedidosNaoTrabalhar = pedidos.getPedidosNaoTrabalhar(data, turno);
		
		//Adiciona os medicos que já estão na escala, que estão de férias e possui pedido para não trabalhar
		List<Medico> remocaoMedicos = new ArrayList<Medico>();
		for (Medico medico : medicosParaEscala) {
			for(String nomeMedico : medicosSalvosNaEscala){
				if(medico.getNome().equals(nomeMedico)){
					remocaoMedicos.add(medico);					
				}
			}
			for(int crmMedico : medicosDeFerias){
				if(medico.getCrm() == crmMedico){
					remocaoMedicos.add(medico);					
				}
			}
			for(String nome : medicoPedidosNaoTrabalhar){
				if(medico.getNome().equals(nome)){
					remocaoMedicos.add(medico);
				}
			}
		}
		
		//Retira os medicos salvos na escala e que estão de férias
		for (Medico medico : remocaoMedicos) {
			medicosParaEscala.remove(medico);
		}
		
		//Exclui os médicos salvos como Hro
		int crmHro = 0;
		try{
			crmHro = escalas.getMedicoHro(dia, mes, ano, turno);
		}catch(AopInvocationException e){ //Se não houver medico M5 ou Hro salvo para o turno e data selecionado é retornado null para o inteiro
			System.out.println("Não há medicos HRO");
		}
		
		//Exclui os médicos salvos como M5
		int crmM5 = 0;
		try{
			crmM5 = escalas.getMedicoM5(dia, mes, ano, turno);
		}catch(AopInvocationException e){
			System.out.println("Não há medicos M5");
		}
		
		medicosParaEscala.remove(medicos.findOne(crmM5));
		medicosParaEscala.remove(medicos.findOne(crmHro));
		
		return medicosParaEscala;
	}
	
	@Transactional
	public boolean salvar(Medico medico) {
		if(medicos.exists(medico.getCrm())){
			return false;
		}else{
			medicos.save(medico);
			return true;
		}		
	}
	
	public void update(Medico medico){
		medicos.save(medico);
	}
	
	public List<Medico> todosMedicos(){
		return medicos.findByAtivo(true);
	}
	
	public List<Medico> medicosPorCategoria(String categoria, boolean ativo){
		return medicos.findByCategoriaAndAtivo(categoria, ativo);
	}

	@Transactional
	public void excluirMedico(Integer crm) {
		Medico medico = medicos.findOne(crm);
		medico.setAtivo(false);
		medicos.save(medico);
	}
	
}
