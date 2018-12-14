package com.sismed.sismedhsd.model.auxiliar;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {
	
	private int cont;
	private int crm;
	private String nome;
	
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Relatorio> getList(List<Object> query){
		
		List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
		for(Object obj : query){
			Object[] o = (Object[]) obj;
			Relatorio relatorio = new Relatorio();
			relatorio.setCont(Integer.parseInt(String.valueOf(o[0])));
			relatorio.setNome(String.valueOf(o[1]));
			relatorio.setCrm(Integer.parseInt(String.valueOf(o[2])));
			listaRelatorio.add(relatorio);
		}
		return listaRelatorio;
		
	}
	
	

}
