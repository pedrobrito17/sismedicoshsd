package com.sismed.sismedhsd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sismed.sismedhsd.model.auxiliar.Relatorio;
import com.sismed.sismedhsd.repository.Relatorios;

@Service
public class RelatorioSemanalService {
	
	private List<Date> semana_1;
	private List<Date> semana_2;
	private List<Date> semana_3;
	private List<Date> semana_4;
	private List<Date> semana_5;
	private List<Date> semana_6;
	
	private int cont;
	
	@Autowired
	private Relatorios relatorios;
	
	public int getQtdSemanas(int mes, int ano){
		getSemanas( getDatas(mes,ano) );
		int contador = 0;
		if(semana_1.size()>0)
			contador++;
		if(semana_2.size()>0)
			contador++;
		if(semana_3.size()>0)
			contador++;
		if(semana_4.size()>0)
			contador++;
		if(semana_5.size()>0)
			contador++;
		if(semana_6.size()>0)
			contador++;
		return contador;
	}
	
	public List<Integer> getFolgaTotalSemana(int mes, int ano){
		getSemanas( getDatas(mes, ano));
		
		List<Integer> contFolgas = new ArrayList<>();
		
		if(semana_1.size()>0)
			contFolgas.add(semana_1.size()*2);
		if(semana_2.size()>0)
			contFolgas.add(semana_2.size()*2);
		if(semana_3.size()>0)
			contFolgas.add(semana_3.size()*2);
		if(semana_4.size()>0)
			contFolgas.add(semana_4.size()*2);
		if(semana_5.size()>0)
			contFolgas.add(semana_5.size()*2);
		if(semana_6.size()>0)
			contFolgas.add(semana_6.size()*2);
		
		return contFolgas;
	}
	
	
	public List<Relatorio> getRelatorioSemanal(int mes, int ano, int num_semana) {
		getSemanas( getDatas(mes,ano) );
		
		switch (num_semana){
			case 0:
				if(semana_1.size()>0){
					return getListaRelatorio(semana_1);}
				else{
					return null;
				}
			case 1:
				if(semana_2.size()>0){
					return getListaRelatorio(semana_2);}
			case 2:
				if(semana_3.size()>0){
					return getListaRelatorio(semana_3);}
			case 3:
				if(semana_4.size()>0){
					return getListaRelatorio(semana_4);}
			case 4:
				if(semana_5.size()>0){
					return getListaRelatorio(semana_5);}
			case 5:
				if(semana_6.size()>0){
					return getListaRelatorio(semana_6);}
			default:
				return null;
		}
	}
	
	@Transactional
	public List<Relatorio> getListaRelatorio(List<Date> semana){
		List<Relatorio> medSocios = new Relatorio().getList(relatorios.getContagemServicoSemanal(semana.get(0), semana.get( semana.size()-1 )));
		List<Relatorio> medHroM5 = new Relatorio().getList(relatorios.getContagemServicoSemanaHRo(semana.get(0), semana.get( semana.size()-1 )));
		
		int cont;
		int identificador;
		
		for(int i = 0 ; i < medSocios.size() ; i++){
			cont = 0;
			for(int j = 0 ; j < medHroM5.size() ; j++){
				if(medSocios.get(i).getCrm() == medHroM5.get(j).getCrm()){
					cont  = medSocios.get(i).getCont() + medHroM5.get(j).getCont();
					medSocios.get(i).setCont(cont);
				}
			}
		}
		
		for(int j = 0 ; j < medHroM5.size() ; j++){
			cont=0;
			identificador=0;
			for(int i = 0 ; i < medSocios.size() ; i++){
				if(medSocios.get(i).getCrm() == medHroM5.get(j).getCrm()){
					identificador++;
				}
			}
			if(identificador==0){
				medSocios.add(medHroM5.get(j));
			}
		}
		
		for(int i = 0 ; i < medSocios.size() ; i++){
			cont = (semana.size()*2) - medSocios.get(i).getCont();
			medSocios.get(i).setCont(cont);
		}
		
		return medSocios;
	}
	
	
	public ArrayList<Date> getDatas(int mes, int ano){
		ArrayList<Date> datas = new ArrayList<Date>();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		Calendar c = new GregorianCalendar();
		
		c.set(GregorianCalendar.MONTH, mes-1);
		c.set(GregorianCalendar.YEAR, ano);
		c.set(GregorianCalendar.DAY_OF_MONTH, 2);
		try {
			for(int i = 1 ; i < c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+1 ; i++){
				d = dt.parse(i+"/0"+mes+"/"+ano);
				datas.add(d);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return datas;
	}
	
	public void getSemanas(ArrayList<Date> datas){
		Calendar cal = new GregorianCalendar();		
	
		semana_1 = new ArrayList<Date>();
		semana_2 = new ArrayList<Date>();
		semana_3 = new ArrayList<Date>();
		semana_4 = new ArrayList<Date>();
		semana_5 = new ArrayList<Date>();
		semana_6 = new ArrayList<Date>();
		cont = 0;
		for (Date date : datas) {
			cal.setTime(date);
			
			/* Primeira semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 0){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) //Finais de semana não entra na contagem de folga
					semana_1.add(date);
			}
			/* Segunda semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 1){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)
					semana_2.add(date);
			}
			/* Terceira semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 2){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)
					semana_3.add(date);
			}
			/* Quarta semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 3){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)	
					semana_4.add(date);
			}
			/* Quinta semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 4){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)
					semana_5.add(date);
			}
			/* Sexta semana */
			if(cal.get(Calendar.DAY_OF_WEEK) <= 7 && cont == 5){
				if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)
					semana_6.add(date);
			}			
			/* Contagem das semanas */
			if(cal.get(Calendar.DAY_OF_WEEK) == 7 || date == datas.get( datas.size()-1 )){
				cont++;
			}
		}
	}	
}
