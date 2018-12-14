package com.sismed.sismedhsd.model.auxiliar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EscalaView {
	
	private int id;
	private String nome;
	private String turno;
	private Date data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public List<EscalaView> getLista(List<Object> ob) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		
		List<EscalaView> ev = new ArrayList<EscalaView>();
		for(Object obj : ob){
			Object[] o = (Object[]) obj;
			
			EscalaView esc = new EscalaView();
			esc.setId(Integer.parseInt(String.valueOf(o[0])));
			esc.setNome(String.valueOf(o[1]));
			esc.setTurno(String.valueOf(o[2]));
			try {
				esc.setData(dt.parse(String.valueOf(o[3])));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ev.add(esc);
		}
		return ev;
	}
	
	
}
