package com.sismed.sismedhsd.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sismed.sismedhsd.model.Escala;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.model.Ordem;
import com.sismed.sismedhsd.model.Pedido;
import com.sismed.sismedhsd.repository.Escalas;
import com.sismed.sismedhsd.repository.Ordens;
import com.sismed.sismedhsd.repository.Pedidos;

@Service
public class PedidoService {

	@Autowired
	private Pedidos pedidos;
	
	@Autowired
	private Escalas escalas;
	
	@Autowired
	private Ordens ordens;
	
	@Autowired
	private EscalaService escalaService;
	
	public List<Pedido> getTodosPedidos(Medico medico) {
		return pedidos.findByMedicoOrderByDataDesc(medico);
	}

	@Transactional
	public void salvarPedido(Pedido pedido) {
		pedidos.save(pedido);
		
		if(pedido.getTipo_pedido()){
			String turno = pedido.getTurno();
			Date data = pedido.getData();
			Medico medico = pedido.getMedico();

			if(turno.equals("todos")){
				adicionarPedidoNaEscala("manha", data, medico);
				adicionarPedidoNaEscala("tarde", data, medico);
				adicionarPedidoNaEscala("noite", data, medico);
			}else{
				adicionarPedidoNaEscala(turno, data, medico);
			}
		}else{
			if(pedido.getTurno().equals("todos")){
				try{
					Escala escala = escalas.findByTurnoAndData( "manha" , pedido.getData() );
					escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
					escala = escalas.findByTurnoAndData( "tarde" , pedido.getData() );
					escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
					escala = escalas.findByTurnoAndData( "noite" , pedido.getData() );
					escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
				}catch(NullPointerException e){
					e.printStackTrace();
				}
			}else{
				try{
					Escala escala = escalas.findByTurnoAndData(pedido.getTurno() , pedido.getData() );
					escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
				}catch(NullPointerException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void adicionarPedidoNaEscala(String turno, Date data, Medico medico){
		
		String nome = escalas.verificarMedicoNaEscala(turno, data, medico.getCrm());
		if(nome!=null){
			return;
		}
		
		Escala escalaSalva = escalas.findByTurnoAndData(turno, data);		
		if(escalaSalva != null){
			Ordem ordem = new Ordem();
			ordem.setEscala(escalaSalva);
			ordem.setMedico(medico);
			ordens.save(ordem);
		}
		else{
			Escala escala = new Escala();
			escala.setData(data);
			escala.setTurno(turno);			
			escalas.save(escala);
			
			Escala escalaSave = escalas.findByTurnoAndData(turno, data);
			Ordem ordem = new Ordem();
			ordem.setEscala(escalaSave);
			ordem.setMedico(medico);
			ordens.save(ordem);
		}
	}
	
	@Transactional
	public void excluirPedido(Integer id) {
		Pedido pedido = pedidos.findOne(id);	
		
		if(pedido.getTipo_pedido()){
			if(pedido.getTurno().equals("todos")){
				Escala escala = escalas.findByTurnoAndData( "manha" , pedido.getData() );
				escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
				
				escala = escalas.findByTurnoAndData( "tarde" , pedido.getData() );
				escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
				
				escala = escalas.findByTurnoAndData( "noite" , pedido.getData() );
				escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
			}else{
				Escala escala = escalas.findByTurnoAndData( pedido.getTurno() , pedido.getData() );
				escalaService.removerMedicoDaEscala( pedido.getMedico().getCrm() , escala.getId());
			}
		}
		
		pedidos.delete(id);
	}

}
