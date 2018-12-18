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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.sismed.sismedhsd.model.Escala;
import com.sismed.sismedhsd.model.EscalaSession;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.model.Ordem;
import com.sismed.sismedhsd.model.auxiliar.Relatorio;
import com.sismed.sismedhsd.service.EscalaService;
import com.sismed.sismedhsd.service.FeriadoService;
import com.sismed.sismedhsd.service.MedicoService;
import com.sismed.sismedhsd.service.OrdemService;
import com.sismed.sismedhsd.service.RelatorioSemanalService;

@Controller
@RequestMapping("/hsdmedicos")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ControllerDragDrop {
	
	@Autowired
	private EscalaSession session;
	
	@Autowired
	private EscalaService escalaService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private OrdemService ordemService;
	
	@Autowired
	private RelatorioSemanalService relatorioSemanalService;
	
	@Autowired
	private FeriadoService feriadoService;
	
	@RequestMapping("/escala")
	public ModelAndView getEscalaDrag(int mes, int ano){
		session.setMes(mes);
		session.setAno(ano);
		ModelAndView mv = new ModelAndView("escalas/escala-drag-drop");
		int[] numCol = {0,1,2,3,4,5,6};
		int[] qtdSem = {0,1,2,3,4,5};
		mv.addObject("datas", getDatas( session.getMes() , session.getAno() ));	
		mv.addObject("qtdsem", qtdSem);
		mv.addObject("qtd", numCol);
		mv.addObject("tituloEscala", getMes( session.getMes() )+" "+ session.getAno() );

		try {
			mv.addObject("escalaM5", escalaService.getEscalasComM5Salvos(session.getMes() , session.getAno()));
			mv.addObject("escalaHro", escalaService.getEscalasComHroSalvos(session.getMes() , session.getAno()));
			mv.addObject("medicosEscalados", escalaService.buscarMedicosEscaladosNoMes(session.getMes() , session.getAno()));
			mv.addObject("feriados", feriadoService.getFeriadosDoMesAno(session.getMes(), session.getAno()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return mv;
	}
	
	@RequestMapping("/escalapdf")
	public ModelAndView getEscalaPDF(){
		ModelAndView mv = new ModelAndView("escalas/escala-pdf");
		int[] numCol = {0,1,2,3,4,5,6};
		int[] qtdSem = {0,1,2,3,4,5};
		mv.addObject("datas", getDatas( session.getMes() , session.getAno() ));	
		mv.addObject("qtdsem", qtdSem);
		mv.addObject("qtd", numCol);
		mv.addObject("tituloEscala", getMes( session.getMes() )+" "+ session.getAno() );

		try {
			mv.addObject("escalaM5", escalaService.getEscalasComM5Salvos(session.getMes() , session.getAno()));
			mv.addObject("escalaHro", escalaService.getEscalasComHroSalvos(session.getMes() , session.getAno()));
			mv.addObject("medicosEscalados", escalaService.buscarMedicosEscaladosNoMes(session.getMes() , session.getAno()));
			mv.addObject("feriados", feriadoService.getFeriadosDoMesAno(session.getMes(), session.getAno()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return mv;
	}
	
	@PostMapping(value="/escaladragdrop/ordenarmedicos/{turno}/{dia}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void updateOrdemDosMedicos(@RequestBody Object crmsMedicos, @PathVariable("turno") String turno, 
			@PathVariable("dia") int dia){
		
		//Recupera os crms e adiciona em uma arraylist
		ArrayList<Integer> crmsMed = new ArrayList<Integer>();
		for(int i = 0 ; i < crmsMedicos.toString().length() ; i=i+6){
			String crm = crmsMedicos.toString().substring((1+i), (5+i));
			crmsMed.add(Integer.parseInt(crm));
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = formato.parse(session.getAno()+"-"+session.getMes()+"-"+dia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Escala escala = escalaService.buscarEscalaPorTurnoSalvo(turno, data);
		ordemService.reordenar(crmsMed, escala.getId());
	}
	
	@DeleteMapping(value="/escaladragdrop/excluirmedico/{id}/{turno}/{dia}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void getExcluirMedicoEscalaDragDrop(@PathVariable("id") int idMedico, @PathVariable("turno") String turno, 
			@PathVariable("dia") int dia, Model model) {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = formato.parse(session.getAno()+"-"+session.getMes()+"-"+dia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Escala escala = escalaService.buscarEscalaPorTurnoSalvo(turno, data);
		escalaService.removerMedicoDaEscala(idMedico, escala.getId());
	}
	
	@GetMapping(value="/escaladragdrop/listamedicos/{turno}/{dia}")
	public String getListaMedicosParaAdicionar(@PathVariable("turno") String turno, 
			@PathVariable("dia") int dia, Model model) {
						
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = formato.parse(session.getAno()+"-"+session.getMes()+"-"+dia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String diaSemana = diaSemana(data);
		
		List<Medico> medicosSocios = medicoService.getMedicoPorTurnoAndDiasemana(turno, diaSemana, data, "socio");
		model.addAttribute("listaMedSoc", medicosSocios);
		
		List<Medico> medicosContratados = medicoService.getMedicoPorTurnoAndDiasemana(turno, diaSemana, data, "contratado");
		model.addAttribute("listaMedCont", medicosContratados);
		
		//Verifica se a escala possui M5 e HRO
		boolean m5 = escalaService.getM5ParaDataETurno(turno, data);
		boolean hro = escalaService.getHroParaDataETurno(turno, data);
		model.addAttribute("m5", m5);
		model.addAttribute("hro", hro);
		
		return "layout/menu-add-medico :: listar-medicos-escala";
	}
	
	@PostMapping(value="/escaladragdrop/salvarmedico/{crm}/{funcao}/{turno}/{dia}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String salvarMedicoNoBd(@PathVariable("crm") int crm, @PathVariable("funcao") String funcao, 
			@PathVariable("turno") String turno, @PathVariable("dia") int dia){
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = formato.parse(session.getAno()+"-"+session.getMes()+"-"+dia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Escala escalaSalva = escalaService.buscarEscalaPorTurnoSalvo(turno,data);
		Medico medico = medicoService.getMedico(crm);
		
		if(escalaSalva != null){
			if(funcao.equals("hro")){
				escalaSalva.setMedicoHro(medico);
				escalaService.salvarMedicoEscala(escalaSalva);
			}
			else if(funcao.equals("m5")){
				escalaSalva.setMedicoM5(medico);
				escalaService.salvarMedicoEscala(escalaSalva);
			}
			else{
				Ordem ordem = new Ordem();
				ordem.setEscala(escalaSalva);
				ordem.setMedico(medico);
				ordemService.salvarOrdemEscala(ordem);				
			}
		}
		else{
			Escala escala = new Escala();
			escala.setData(data);
			escala.setTurno(turno);
			if(funcao.equals("hro")){
				escala.setMedicoHro(medico);
				escalaService.salvarMedicoEscala(escala);
			}
			else if(funcao.equals("m5")){
				escala.setMedicoM5(medico);
				escalaService.salvarMedicoEscala(escala);
			}
			else{
				escalaService.salvarMedicoEscala(escala);
				Escala escalaSave = escalaService.buscarEscalaPorTurnoSalvo(turno, data);
				Ordem ordem = new Ordem();
				ordem.setEscala(escalaSave);
				ordem.setMedico(medico);
				ordemService.salvarOrdemEscala(ordem);
			}
		}
		return medico.getNome()+" salvo";
	}
	
	@RequestMapping(value="/modal/relatoriosemanal", method=RequestMethod.GET)
	public String getModalRelatorioSemanal(Model model) {
		model.addAttribute("tituloRelatorio", "Relatório semanal: "+getMes( session.getMes() )+" de "+ session.getAno() );
		model.addAttribute("qtd_semanas", getQtdSemanas());
		
		/*qtd turnos totais da semana*/
		model.addAttribute("sem", relatorioSemanalService.getFolgaTotalSemana(session.getMes(), session.getAno()));
		
		/*qtd turnos de folga por semana*/
		
		//Verifica se a primeira semana tem dias
		//Se a primeira semana começar no fim de semana, a semana 2 deverá ser a semana 1
		List<Relatorio> info = relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 0);	
		
		if(info!=null){		
			model.addAttribute("semana_1", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 0));
			model.addAttribute("semana_2", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 1));
			model.addAttribute("semana_3", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 2));
			model.addAttribute("semana_4", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 3));
			model.addAttribute("semana_5", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 4));
			model.addAttribute("semana_6", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 5));
		}
		else{			
			model.addAttribute("semana_1", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 1));
			model.addAttribute("semana_2", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 2));
			model.addAttribute("semana_3", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 3));
			model.addAttribute("semana_4", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 4));
			model.addAttribute("semana_5", relatorioSemanalService.getRelatorioSemanal(session.getMes(), session.getAno(), 5));
		}
		
		return "layout/tabela-relatorio-semanal :: tabela-relatorio-semanal";
	}
	
	
	
	
	/* =========================== UTILS =========================== */
	public ArrayList<Date> getDatas(int mes, int ano){
		ArrayList<Date> datas = new ArrayList<Date>();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = null;
		Calendar c = new GregorianCalendar();
		
		c.set(GregorianCalendar.MONTH, mes-1);
		c.set(GregorianCalendar.YEAR, ano);
		c.set(GregorianCalendar.DAY_OF_MONTH, 2);
		try {
			for(int i = 1 ; i < c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+1 ; i++){
				d = dt.parse(i+"/0"+mes+"/"+ano+" 10:00:00");
				datas.add(d);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return datas;
	}
	
	public String diaSemana(Date data){
		GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(data);
        int diaS = gc.get(GregorianCalendar.DAY_OF_WEEK);
        
        switch(diaS){
        	case 1:
        		return "domingo";
        	case 2:
        		return "segunda-feira";
        	case 3:
        		return "terca-feira";
        	case 4:
        		return "quarta-feira";
        	case 5:
        		return "quinta-feira";
        	case 6:
        		return "sexta-feira";
        	case 7:
        		return "sabado";
        	default:
        		return "nulo";
        }		
	}
	
	public String getMes(int mes){
		switch(mes){
			case 1:
				return "Janeiro";
			case 2:
				return "Fevereiro";
			case 3:
				return "Março";
			case 4:
				return "Abril";
			case 5:
				return "Maio";
			case 6:
				return "Junho";
			case 7:
				return "Julho";
			case 8:
				return "Agosto";
			case 9:
				return "Setembro";
			case 10:
				return "Outubro";
			case 11:
				return "Novembro";
			default:
				return "Dezembro";
		}
	}
	
	public int[] getQtdSemanas(){
		int qtdSemanas = relatorioSemanalService.getQtdSemanas(session.getMes(), session.getAno());
		int [] vetors = new int[qtdSemanas];
		int cont = 1;
		for(int i = 0 ; i < qtdSemanas ; i++){
			vetors[i]=cont;
			cont++;
		}
		return vetors;
	}

}
