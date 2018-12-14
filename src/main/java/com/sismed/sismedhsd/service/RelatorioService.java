package com.sismed.sismedhsd.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.auxiliar.Relatorio;
import com.sismed.sismedhsd.repository.Medicos;
import com.sismed.sismedhsd.repository.Relatorios;

@Service
public class RelatorioService {
	
	@Autowired
	private Relatorios relatorios;
	
	@Autowired
	private Medicos medicos;
	
	@Transactional
	public Set<String> getNomeMedicos(int mes, int ano, String categoria){
		Set<String> nomes = medicos.getNomeMedicos(mes, ano, categoria);
		Set<String>	nomesM5 = medicos.getNomeM5(mes, ano, categoria);
		Set<String> nomesHRO = medicos.getNomeHro(mes, ano, categoria);
		
		for(String nome : nomesM5){
			nomes.add( nome );
		}
		for(String nome : nomesHRO){
			nomes.add( nome );
		}
		
		return nomes;
	}
	
	public List<Relatorio> getHroParaTabela(int mes, int ano, String categoria){
		return new Relatorio().getList(relatorios.getContagemHro(mes, ano, categoria));
	}
	
	public List<Relatorio> getM5ParaTabela(int mes, int ano, String categoria){
		return new Relatorio().getList(relatorios.getContagemM5(mes, ano, categoria));
	}

	public List<Relatorio> getMedicosManha(int mes, int ano, String categoria) {
		return new Relatorio().getList(relatorios.getMedicosManha(mes, ano, categoria));
	}
	
	@Transactional
	public List<Relatorio> getQuantTurnos(int mes, int ano){
		Calendar datas = new GregorianCalendar();
        datas.set(ano, mes-1, 1);
        int quantidadeDias = datas.getActualMaximum (Calendar.DAY_OF_MONTH);  
        int qtdDomingos = quantDomingos(mes, ano);
        int qtdSabados = quantSabados(mes, ano);
        
        /*Cada dia possui 2 turnos(manha e tarde)*/
        int qtdTurnos = (quantidadeDias - qtdDomingos - qtdSabados)*2;
		
		List<Relatorio> turnosFolga = new Relatorio().getList(relatorios.getQtdTurnosDeFolga(qtdTurnos, mes, ano));
		List<Relatorio> turnosM5 = new Relatorio().getList(relatorios.getContagemM5(mes, ano, "socio"));
		List<Relatorio> turnoHro = new Relatorio().getList(relatorios.getContagemHro(mes, ano, "socio"));
		
		for(Relatorio rel : turnosFolga){
			for(Relatorio m5 : turnosM5){
				if(rel.getNome().equals(m5.getNome())){
					int cont = rel.getCont() - m5.getCont();
					rel.setCont(cont);
				}
			}
			for(Relatorio hro : turnoHro){
				if(rel.getNome().equals(hro.getNome())){
					int cont = rel.getCont() - hro.getCont();
					rel.setCont(cont);
				}
			}
		}
		
		return turnosFolga;
	}
	
	public static int quantDomingos( int mes, int ano ) {
	    int quantDomingos = 0;
	    // cria um calendário na data 01/mes/ano
	    Calendar c = new GregorianCalendar( ano, mes - 1, 1 );
	    do {
	        // o dia da semana é domingo?
	        if ( c.get( Calendar.DAY_OF_WEEK ) == Calendar.SUNDAY ) {
	            quantDomingos++;
	        }
	        // incrementa um dia no calendário
	        c.roll( Calendar.DAY_OF_MONTH, true );
	        // enquanto o dia do mês atual for diferente de 1
	    } while ( c.get( Calendar.DAY_OF_MONTH ) != 1 );
	    return quantDomingos;
	}
	
	public static int quantSabados( int mes, int ano ) {
	    int quantSabados = 0;
	    // cria um calendário na data 01/mes/ano
	    Calendar c = new GregorianCalendar( ano, mes - 1, 1 );
	    do {
	        // o dia da semana é domingo?
	        if ( c.get( Calendar.DAY_OF_WEEK ) == Calendar.SATURDAY ) {
	        	quantSabados++;
	        }
	        // incrementa um dia no calendário
	        c.roll( Calendar.DAY_OF_MONTH, true );
	        // enquanto o dia do mês atual for diferente de 1
	    } while ( c.get( Calendar.DAY_OF_MONTH ) != 1 );
	    return quantSabados;
	}

	public List<Relatorio> getQtdOrdemTarde(int mesEscala, int anoEscala, int posicao) {
		
		return new Relatorio().getList(relatorios.getQtdOrdemTarde(mesEscala, anoEscala, posicao));
	}

	public List<Relatorio> getQtdOrdemNoite(int mesEscala, int anoEscala, int posicao) {
		return new Relatorio().getList(relatorios.getQtdOrdemNoite(mesEscala, anoEscala, posicao));
	}

	public List<Relatorio> getQtdFds(int mesEscala, int anoEscala) {
		
		return new Relatorio().getList( relatorios.getCountFds(mesEscala, anoEscala ) );
		
	}

	public List<Relatorio> getContTardes(int mesEscala, int anoEscala, String categoria) {
		return new Relatorio().getList(relatorios.getContagemTardes(mesEscala, anoEscala, categoria));
	}

	public List<Relatorio> getContNoites(int mesEscala, int anoEscala, String categoria) {
		return new Relatorio().getList(relatorios.getContagemNoites(mesEscala, anoEscala, categoria));
	}

}
