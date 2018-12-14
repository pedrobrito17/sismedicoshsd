package com.sismed.sismedhsd.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import com.sismed.sismedhsd.model.EscalaSession;
import com.sismed.sismedhsd.model.auxiliar.Relatorio;
import com.sismed.sismedhsd.service.RelatorioSemanalService;
import com.sismed.sismedhsd.service.RelatorioService;

@Controller
@RequestMapping("/hsdmedicos")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class RelatorioController {
	
	@Autowired
	private EscalaSession session;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private RelatorioSemanalService relatorioSemanalService;

	private int[] vetors;
	
	@RequestMapping("/novorelatoriosocios")
	public ModelAndView getPageRelatorioSocios(){
		ModelAndView mv = new ModelAndView("relatorio/gerar-relatorio-socios");
		return mv;
	}
	
	@RequestMapping("/novorelatoriocontratados")
	public ModelAndView getPageRelatorioContratados(){
		ModelAndView mv = new ModelAndView("relatorio/gerar-relatorio-contratados");
		return mv;
	}
	
	@RequestMapping("/novorelatoriosemanal")
	public ModelAndView getPageRelatorioSemanal(){
		ModelAndView mv = new ModelAndView("relatorio/gerar-relatorio-semanal");
		return mv;
	}
	
	@GetMapping("/relatoriosocios")
	public ModelAndView visualizarRelatorioSocios(int mes, int ano){
		session.setMes(mes);
		session.setAno(ano);
		ModelAndView mv = new ModelAndView("relatorio/relatorio-socios");				
		mv.addObject("tituloRelatorio", "Relatório dos médicos sócios: "+getMes( session.getMes() )+" de "+ session.getAno() );	
		mv.addObject("nomeMedicos", relatorioService.getNomeMedicos( session.getMes() , session.getAno() , "socio"));
		mv.addObject("contM5", relatorioService.getM5ParaTabela( session.getMes() , session.getAno() , "socio"));
		mv.addObject("contHro", relatorioService.getHroParaTabela(session.getMes() , session.getAno(), "socio"));
		mv.addObject("contManha", relatorioService.getMedicosManha(session.getMes() , session.getAno(), "socio"));
		mv.addObject("contTurnosFolga", relatorioService.getQuantTurnos(session.getMes() , session.getAno()));

		mv.addObject("lista_t1", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 1));
		mv.addObject("lista_t2", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 2));
		mv.addObject("lista_t3", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 3));
		mv.addObject("lista_t4", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 4));
		mv.addObject("lista_t5", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 5));
		mv.addObject("lista_t6", relatorioService.getQtdOrdemTarde(session.getMes() , session.getAno(), 6));
		
		mv.addObject("lista_n1", relatorioService.getQtdOrdemNoite(session.getMes() , session.getAno(), 1));
		mv.addObject("lista_n2", relatorioService.getQtdOrdemNoite(session.getMes() , session.getAno(), 2));
		
		mv.addObject("qtd_fds", relatorioService.getQtdFds(session.getMes() , session.getAno()));
		return mv;
	}
	
	@GetMapping("/relatoriocontratados")
	public ModelAndView visualizarRelatorioContratados(int mes, int ano){
		session.setMes(mes);
		session.setAno(ano);
		ModelAndView mv = new ModelAndView("relatorio/relatorio-contratados");				
		mv.addObject("tituloRelatorio", "Relatório dos médicos contratados: "+getMes( session.getMes() )+" de "+ session.getAno() );	
		mv.addObject("nomeMedicos", relatorioService.getNomeMedicos(session.getMes() , session.getAno(), "contratado"));
		mv.addObject("contM5", relatorioService.getM5ParaTabela(session.getMes() , session.getAno(), "contratado"));
		mv.addObject("contHro", relatorioService.getHroParaTabela(session.getMes() , session.getAno(), "contratado"));
		mv.addObject("contManha", relatorioService.getMedicosManha(session.getMes() , session.getAno(), "contratado"));
		
		mv.addObject("contTardes", relatorioService.getContTardes(session.getMes() , session.getAno(), "contratado"));
		mv.addObject("contNoites", relatorioService.getContNoites(session.getMes() , session.getAno(), "contratado"));
		
		return mv;
	}
	
	@GetMapping("/relatoriosemanal")
	public ModelAndView visualizarRelatorioSemanal(int mes, int ano){
		session.setMes(mes);
		session.setAno(ano);
		ModelAndView mv = new ModelAndView("relatorio/relatorio-semanal");
		mv.addObject("tituloRelatorio", "Relatório semanal: "+getMes( session.getMes() )+" de "+ session.getAno() );
		mv.addObject("qtd_semanas", getQtdSemanas());
		
		/*qtd turnos totais da semana*/
		mv.addObject("sem", relatorioSemanalService.getFolgaTotalSemana(session.getMes(), session.getAno()));
		
		/*qtd turnos de folga por semana*/
		
		//Verifica se a primeira semana tem dias
		//Se a primeira semana começar no fim de semana, a semana 2 deverá ser a semana 1
		List<Relatorio> info = relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 0);	
		
		if(info!=null){
			mv.addObject("semana_1", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 0));
			mv.addObject("semana_2", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 1));
			mv.addObject("semana_3", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 2));
			mv.addObject("semana_4", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 3));
			mv.addObject("semana_5", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 4));
			mv.addObject("semana_6", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 5));
		}
		else{
			mv.addObject("semana_1", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 1));
			mv.addObject("semana_2", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 2));
			mv.addObject("semana_3", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 3));
			mv.addObject("semana_4", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 4));
			mv.addObject("semana_5", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 5));
		}
		
		return mv;
	}
	
	public int[] getQtdSemanas(){
		int qtdSemanas = relatorioSemanalService.getQtdSemanas(session.getMes(), session.getAno());
		vetors = new int[qtdSemanas];
		int cont = 1;
		for(int i = 0 ; i < qtdSemanas ; i++){
			vetors[i]=cont;
			cont++;
		}
		return vetors;
	}
		
	public ArrayList<Date> getDatas(int mes, int ano){
		ArrayList<Date> datas = new ArrayList<Date>();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		Calendar c = new GregorianCalendar();
		c.set(Calendar.MONTH, mes-1);
		try {
			for(int i = 1 ; i < c.getActualMaximum(Calendar.DAY_OF_MONTH)+1 ; i++){
				d = dt.parse(i+"/0"+mes+"/"+ano);
				datas.add(d);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return datas;
	}
	
	public String getMes(int mes){
		switch(mes){
			case 1:
				return "janeiro";
			case 2:
				return "fevereiro";
			case 3:
				return "março";
			case 4:
				return "abril";
			case 5:
				return "maio";
			case 6:
				return "junho";
			case 7:
				return "julho";
			case 8:
				return "agosto";
			case 9:
				return "setembro";
			case 10:
				return "outubro";
			case 11:
				return "novembro";
			default:
				return "dezembro";
		}
	}
}
